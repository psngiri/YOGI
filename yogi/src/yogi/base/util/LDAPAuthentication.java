package yogi.base.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LDAPAuthentication {
	public static String LdapUrl = "ldaps://pdcdc0020001.corpaa.aa.com:636";
	public static String Domain = "@corpaa.aa.com";
	public static boolean Debug = false;
	
	public static LdapContext getLdapContext(String userId, String password){ 
		LdapContext ctx = null;
		
		if(password == null || password.trim().isEmpty())
			return ctx;
		
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, userId + Domain);
			env.put(Context.SECURITY_CREDENTIALS, password);
			env.put(Context.PROVIDER_URL, LdapUrl);
			ctx = new InitialLdapContext(env, null);
			if (Debug) System.out.println("Connection Successful.");
		} catch (NamingException nex) {
			//if (Debug) 
				nex.printStackTrace();
		}
		return ctx;
	}
	
	public static boolean isAuthenticUser(String userId, String password)
	{
		return getLdapContext(userId, password) != null;
	}
}
