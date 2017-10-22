package com.icedust.keystorehelper.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;

public class KeyStoreHelper {
	public static void createTrustJKSKeyStore(final String originalTrustFolder,final String jksTrustStoreLocation, final String password) throws Exception {
		File keyStoreFile = new File(jksTrustStoreLocation);
		boolean isIncludedCer=false;
		if (!keyStoreFile.exists()) {
			try {
				KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
				keystore.load(null, password.toCharArray());
				File trustedFolder = new File(originalTrustFolder);
				File[] certs = trustedFolder.listFiles();
				if (certs != null) {
					for (File cert : certs) {
						CertificateFactory factory = CertificateFactory.getInstance("X.509");
						try {
							X509Certificate certificate = (X509Certificate) factory.generateCertificate(new FileInputStream(cert));
							X500Principal principal = certificate.getSubjectX500Principal();
							LdapName ldapDN = new LdapName(principal.getName());
							List<Rdn> rdns = ldapDN.getRdns();
							for (Rdn rdn : rdns) {
								String type = rdn.getType();
								if (type.equals("CN")) {
									keystore.setCertificateEntry((String) rdn.getValue(),certificate);
									isIncludedCer=true;
									break;
								}
							}
						} catch (Exception ex) {
							continue;
						}
					}
				}
                if(!isIncludedCer){
                	throw new Exception("The selected CA folder didn't include any CA Certification file");
                }
				FileOutputStream fos = new FileOutputStream(jksTrustStoreLocation);
				keystore.store(fos, password.toCharArray());
				fos.close();
			} catch (Exception exp) {
				throw exp;
			}
		}
	}


}
