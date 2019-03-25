package apps.my.p2017;

import com.google.gson.annotations.SerializedName;


public class GithubRepo {

    Long id;
    String name;
    String full_name;

    @SerializedName("private")
    Boolean is_public;

    String description;
    String url;


}
