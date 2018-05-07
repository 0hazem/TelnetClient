import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ADSLService {
  //  RouterInfo routerinfo = new RouterInfo();
    //RequestBody body = RequestBody.create(MediaType.parse("application/json"), routerinfo.toString());
    @POST("/")
    Call <Void> sendRouterData (@Body DiagnosticData data);

}
