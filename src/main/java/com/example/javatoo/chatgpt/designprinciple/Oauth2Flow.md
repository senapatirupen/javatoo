OAuth 2.0 is an authorization framework that allows users to grant limited access to their protected resources on one website or application to another website or application without sharing their credentials. The OAuth 2.0 flow involves several parties: the client application (also known as the third-party application), the user, the authorization server, and the resource server. Here's a high-level explanation of the OAuth 2.0 flow:

1. Client Registration: The client application registers with the authorization server and obtains a client ID and client secret. These credentials are used to identify and authenticate the client application.

2. User Authorization Request: The client application initiates the OAuth flow by redirecting the user to the authorization server. The redirect includes the client ID and the requested scopes (permissions) that the client application wants to access.

3. User Consent: The user is presented with a consent screen by the authorization server. The consent screen explains the requested permissions and asks the user to grant or deny access to their protected resources.

4. Authorization Grant: If the user grants access, the authorization server generates an authorization grant (e.g., authorization code) and redirects the user back to the client application's specified redirect URL, along with the authorization grant.

5. Access Token Request: The client application sends a request to the authorization server, including the authorization grant received in the previous step and the client credentials (client ID and client secret). This request is made in a secure manner, typically over HTTPS.

6. Access Token Issuance: The authorization server verifies the authorization grant and the client credentials. If valid, it issues an access token to the client application. The access token represents the client application's authorization to access the user's protected resources.

7. Resource Access: The client application uses the received access token to make authorized requests to the resource server (API) on behalf of the user. The access token is included in the request headers or as a query parameter.

8. Resource Server Validation: The resource server verifies the received access token. It checks the token's validity, expiration, and scope to ensure the client application is authorized to access the requested resources.

9. Protected Resource Response: If the access token is valid and the client application is authorized, the resource server responds to the client application's request with the requested data or performs the requested action.

The OAuth 2.0 flow allows users to grant limited access to their resources without sharing their credentials. By using access tokens, the client application can securely access the user's protected resources on the resource server. The access tokens have an expiration time and limited scope, ensuring a controlled and secure access mechanism.