package inductionlabs.tervelhd;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class  Sender 
{
public static void share_post(String siteb)
{Intent intent = new Intent(Intent.ACTION_SEND);
intent.setType("text/plain");
intent.putExtra(Intent.EXTRA_TITLE,"Velocity");
intent.putExtra(Intent.EXTRA_TEXT,"Proof you are fast enough n faster than your friend Get your free copy of terminal velocity from : https://market.android.com/details?id=inductionsoftware.pnr");
TerVel.contex.startActivity(Intent.createChooser(intent, "Sharing is Caring"));
}


public static void openWebURL( String inURL ,Context c)
{
    Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );
    c.startActivity( browse );
}


}



