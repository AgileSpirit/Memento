package io.memento.application.request;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    21/08/2014
 */
public class ConnectionRequest {

    /**
     *  Exemple de réponse authResult :
     *
     *  authResult : {
     *      _aa: "0"
     *      access_token: "ya29.aQCCijrA_kMTghwAAAAyomMW6vYJ1zFgvpvp2uc109dFm5LxisqM8m-D-Vlqfg"
     *      authuser: "0"
     *      client_id: "276048965691-5mbhe3lasuea0gu49o8h14jboeo8tua6.apps.googleusercontent.com"
     *      code: "4/tLAj0hDfzg-zP2rXH0jcKg442evg.gnUxS9ac98IUoiIBeO6P2m-c_Qf5jwI"
     *      cookie_policy: "single_host_origin"
     *      expires_at: "1408694295"
     *      expires_in: "3600"
     *      g_user_cookie_policy: "single_host_origin"
     *      id_token: "eyJhbGciOiJSUzI1NiIsImtpZCI6IjZkYzJjYTNmM2Q3ZjA3YzBiMmUzNTZmY2E3N2JmOWY1OWQzZjU4ZGMifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwic3ViIjoiMTE3MDI0Njc3NzQ3ODg1NjQ3OTA0IiwiYXpwIjoiMjc2MDQ4OTY1NjkxLTVtYmhlM2xhc3VlYTBndTQ5bzhoMTRqYm9lbzh0dWE2LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXRfaGFzaCI6IjBxZElVRmlEbWR6dHU2SWFTQUJaWkEiLCJhdWQiOiIyNzYwNDg5NjU2OTEtNW1iaGUzbGFzdWVhMGd1NDlvOGgxNGpib2VvOHR1YTYuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJjX2hhc2giOiJlOUJXZTJhdjZLYTJNUk50TTU4XzB3IiwiaWF0IjoxNDA4NjkwMzk0LCJleHAiOjE0MDg2OTQyOTR9.F0TTso0-Citt9d8sPPVEGoemXfIbMbb9Zln7qEBz8PXqMMrW4MLFOt7USfzZfh6fDwcnSjyrv-wKGXpGwVTNKgTIMDgLXQiVd70RjkMWrKcZIWRfpD7sxvDO0H0AH7618-apljjxbOcnOdC0iD19ilTQ6qWuL6wtefJl4faJ5Xk"
     *      issued_at: "1408690695"
     *      num_sessions: "3"
     *      prompt: "none"
     *      response_type: "code token id_token gsession"
     *      scope: "https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.moments.write https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/plus.profile.agerange.read https://www.googleapis.com/auth/plus.profile.language.read https://www.googleapis.com/auth/plus.circles.members.read"
     *      session_state: "0d7d6536c6529e78df7f9fd6c69902ff68292a63..0f4a"
     *      state: ""
     *      status: {
     *          google_logged_in: true
     *          method: "AUTO"
     *          signed_in: true
     *      }
     *      token_type: "Bearer"
     *  }
     */

    /*
     * ATTRIBUTES
     */

    private String access_token;
//    private String authuser;
    private String client_id;
//    private String code;
//    private String cookie_policy;
//    private String expires_at;
//  private String expires_in;
//  private String g_user_cookie_policy;
    private String id_token;
//    private String issued_at;
//  private String num_sessions;
//  private String prompt;
//  private String response_type;
//  private String scope;
//  private String session_state;
//  private String state;
//  private String status;
//  private String token_type;

    /*
     * GETTERS & SETTERS
     */

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }
}
