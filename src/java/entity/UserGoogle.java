package entity;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author twna21
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserGoogle implements Serializable {
 
    private String id;
    private String email;
    private boolean verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    
      public static UserGoogle getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogle googlePojo = new Gson().fromJson(response, UserGoogle.class
        );

        return googlePojo;
    }
      
      public static void main(String[] args) {
        UserGoogle u = new UserGoogle();
        u.getPicture();
    }
    
}
