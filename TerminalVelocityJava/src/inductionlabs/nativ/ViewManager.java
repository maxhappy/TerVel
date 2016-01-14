package inductionlabs.nativ;



 
    
public class ViewManager
{
public int screenstat;

public ViewManager()
{
this.screenstat=0;

}


public void SetState(int screenstate)
{	 screenstat=screenstate;
}
public int GetState()
{
    return screenstat;
}


}