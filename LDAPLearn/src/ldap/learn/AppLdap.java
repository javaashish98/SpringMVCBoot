package ldap.learn;

import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;



public class AppLdap {
	
	public void addUser() {
		System.out.println("This project will provide information to connecto to ldap server");
		DirContext connection=getConnection();
		Attributes attributes=new BasicAttributes();
		Attribute attribute=new BasicAttribute("objectClass");
		attribute.add("inetOrgPerson");
		attributes.put(attribute);
		
		attributes.put("sn","singh");
		try {
			connection.createSubcontext("cn=john,ou=users,o=nit1",attributes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Sucess");
	}
	
	
	public static DirContext getConnection() {
		DirContext connection=null;
		
			Properties env=new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
			env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
			env.put(Context.SECURITY_CREDENTIALS, "secret");
			try {
				connection=new InitialDirContext(env);
			}
			catch(AuthenticationException ex) {
				ex.printStackTrace();
				
			}
			catch(NamingException ne) {
				ne.printStackTrace();
				
			}
			return connection;
		}
			

	public void addUserToGroup(String user,String group) {
		ModificationItem[] items=new ModificationItem[1];
		DirContext connection=getConnection();

		Attribute attribute=new BasicAttribute("uniqueMember","cn="+user+",ou=users,o=nit1");
		items[0]=new ModificationItem(DirContext.ADD_ATTRIBUTE, attribute);
		try {
			connection.modifyAttributes("cn="+group+",ou=groups,o=nit1", items);
			System.out.println("Group Added successfully");
		}catch(Exception e) {
			
		}
		
	}
	public void getAllUsers() throws NamingException {

//		String searchFilter="(objectClass=inetOrgPerson)";
		String searchFilter="(objectClass=groupOfUniqueNames)";
		String[] reqAttr= {"cn"};
		SearchControls controls=new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(reqAttr);
		DirContext connection=getConnection();
		//NamingEnumeration users=connection.search("ou=users,ou=system", searchFilter, controls);
		//NamingEnumeration users=connection.search("ou=users,o=nit1", searchFilter, controls);
		NamingEnumeration users=connection.search("ou=groups,o=nit1", searchFilter, controls);
		SearchResult result=null;
	//	System.out.println("No element");
		while(users.hasMore()) {
			result=(SearchResult)users.next();
			javax.naming.directory.Attributes attr=result.getAttributes();
			System.out.println(attr.get("cn"));
//			System.out.println(attr.getAll().toString());
		}
	}
	
	public void addUid() {
		DirContext connection=getConnection();
		Attributes attributes=new BasicAttributes();
		Attribute attribute=new BasicAttribute("objectClass");
		attribute.add("inetOrgPerson");
		attributes.put(attribute);
		
		attributes.put("uid","john");
		try {
			connection.createSubcontext("cn=john,ou=users,o=nit1",attributes);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Sucess");
	}
	
	public void CreateOrUpdatePassword() {
		
	}
	public static void main(String[] args)  {
	
		DirContext connection=getConnection();
		AppLdap ldap=new AppLdap();
		//try {
		//ldap.getAllUsers();
		//}catch (Exception e) {
		//	e.printStackTrace();
		//}
		//ldap.addUser();
		//ldap.addUserToGroup("john", "Admin");
		ldap.addUid();
		System.out.println(connection);
	}
}
