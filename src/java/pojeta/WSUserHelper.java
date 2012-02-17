/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojeta;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

/** Jersey REST client generated for REST resource:UserFacadeREST [users]<br>
 *  USAGE:<pre>
 *        WSUserHelper client = new WSUserHelper();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 *  </pre>
 * @author michael
 */
public class WSUserHelper {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "https://luckycode.be:8181/projeta-webservice/resources";

    public WSUserHelper() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig(); // SSL configuration
        config.getProperties().put(com.sun.jersey.client.urlconnection.HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new com.sun.jersey.client.urlconnection.HTTPSProperties(getHostnameVerifier(), getSSLContext()));
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("users");
    }

    public String createNewUser(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("create").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(String.class, requestEntity);
    }

    public String userExists(String username) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("exists/{0}", new Object[]{username}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void remove(String id) throws UniformInterfaceException {
        webResource.path(java.text.MessageFormat.format("{0}", new Object[]{id})).delete();
    }

    public String countREST() throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("count");
        return resource.accept(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void edit(Object requestEntity) throws UniformInterfaceException {
        webResource.path("update").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(requestEntity);
    }

    public String findByUsergroup(String usergroupid) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("usergroup/{0}", new Object[]{usergroupid}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String findByUsername(String username, String userId) throws UniformInterfaceException {
        WebResource resource = webResource;
        if (username != null) {
            resource = resource.queryParam("username", username);
        }
        if (userId != null) {
            resource = resource.queryParam("userId", userId);
        }
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String setPassword(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("setPassword").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(String.class, requestEntity);
    }

    public <T> T findRange(Class<T> responseType, String from, String to) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String findAll2() throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path("all");
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public <T> T find(Class<T> responseType, String id) throws UniformInterfaceException {
        WebResource resource = webResource;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.destroy();
    }

    public void setUsernamePassword(String username, String password) {
        client.addFilter(new com.sun.jersey.api.client.filter.HTTPBasicAuthFilter(username, password));
    }

    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        };
    }

    private SSLContext getSSLContext() {
        javax.net.ssl.TrustManager x509 = new javax.net.ssl.X509TrustManager() {

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                return;
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("SSL");
            ctx.init(null, new javax.net.ssl.TrustManager[]{x509}, null);
        } catch (java.security.GeneralSecurityException ex) {
        }
        return ctx;
    }
    
    public String createNewUserWithUserRole(Object requestEntity) throws UniformInterfaceException {
        return webResource.path("createWithUserRole").type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(String.class, requestEntity);
    }
    
    
}
