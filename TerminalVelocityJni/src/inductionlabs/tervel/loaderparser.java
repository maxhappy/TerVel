package inductionlabs.tervel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


import com.badlogic.androidgames.framework.FileIO;
public class loaderparser {
	static final String COMMA = ",";
	static final String SEMICOMMA = ";";
	static final String COLON = ":";
 

public static void loadindex(FileIO files,String file) 
{
	  
	InputStreamReader in=null;
	BufferedReader bf=null;
	   try 
	    {
	    in = new InputStreamReader(files.readAsset(file));
	    bf=new BufferedReader(in);
        Assets.loaderp=0;
	    parse(files,bf);
	    bf.close();
       }
	  catch (IOException e) 
	  {
	  } catch (NumberFormatException e) 
	  {
	    // :/ It's ok, defaults save our day
	  } finally 
	  {
	    try {
	        if (in != null)
	            in.close();
	        if(bf != null)
	        	bf.close();
	    } catch (IOException e) {
	    }
	}
	
	
}


private static void parse(FileIO files, BufferedReader bf) 
{ 
  String line; 
try 
{
	line = bf.readLine();
	
 int index=0;
  while (line != null) 
  {  if(line.equals(":Packs"))
		  {line = bf.readLine();
	     index=1;
		  }
     else if(line.equals(":Fonts"))
	  {line = bf.readLine();
	  index=2;
	  }
      else if(line.equals(":Music"))
      {line = bf.readLine();
      index=3;
      }
      else if(line.equals(":Sound"))
      {line = bf.readLine();
       index=4;
      }
      else if(line.equals(":patt"))
      {line = bf.readLine();
       index=5;
      }
     else 
     { 
	  switch(index)
	  {case 1:packloader(files,line+".pack");  Assets.loaderp=10;break;
	   case 2:fontloader(files,line);Assets.loaderp=20;break;
	   case 3:musicloader(files,line);Assets.loaderp=30;break;
	   case 4:soundloader(files,line);Assets.loaderp=40;break;
	   case 5:patternloader(files,line);Assets.loaderp=50;break;
	  }
	  line = bf.readLine();
  }
  }
} catch (IOException e) 
{
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}


private static void patternloader(FileIO files, String line)
{
	 
	
}


private static void soundloader(FileIO files, String file) 
{
	 Assets.SoundNames.add(file);
	 Assets.soundcount++;
	
}


private static void musicloader(FileIO files, String file) 
{
	 Assets.MusicNames.add(file);
	 Assets.MusicCount++;
	
	// TODO Auto-generated method stub
	
}


private static void fontloader(FileIO files, String file) 
{
 Assets.FontNames.add(file+".png");
 packloader(files,file+".pack");
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////.....parse the .pack file and load its data .../////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

private static void packloader(FileIO files, String file)
{
InputStreamReader in=null;
BufferedReader bf=null;
try 
{
in = new InputStreamReader(files.readAsset(file));
bf=new BufferedReader(in);
parsepack(files,bf);
bf.close();
}
catch (IOException e) 
{
} catch (NumberFormatException e) {
// :/ It's ok, defaults save our day
} finally {
try {
    if (in != null)
        in.close();
    if (bf != null)
        bf.close();
    
} catch (IOException e) {
}
}
	
}


private static void parsepack(FileIO files, BufferedReader bf) 
{
	  
	 
	@SuppressWarnings("unused")
	String line= null ,texturename = null,texturegionname= null,format= null,filter= null,filter1= null,repeat= null,sub= null,sub1= null,sub2= null;
	@SuppressWarnings("unused")
	Boolean rotate=false;
	int x=0,y=0,sizex=0,sizey=0,orizx=0,orizy = 0,offsetx=0,offsety=0,index=0;
	String [] tokens={".png","format:","filter:","repeat:","rotate:","xy:","size:","orig:","offset:","index:"};
	StringTokenizer st=null;
	try 
	 {
	 line= bf.readLine();
	 while (line != null) 
	 {
	 if(line.equals(""))
	 line=bf.readLine();		 
	  st=new StringTokenizer(line,COLON);
	  
	   int i=0;
	   while(i<tokens.length)
	   { if(line.contains(tokens[i]))	
	     break; 
	     else
	     i++;
	   }
	   if(line.equals("")){i++;}
	   else if(st.countTokens()!=0)
	   {if(line.indexOf(":")!=-1)
	     { sub=line.substring(line.indexOf(":")+2);
	       if(line.indexOf(",")!=-1)
	       {sub1=sub.substring(0,sub.indexOf(","));
	       sub2=sub.substring(sub.indexOf(",")+2);
	      }
	     }
	   }
	   switch(i)
	   {case 0:texturename=line;break;
	    case 1:format=sub;break;
	    case 2:filter=sub1;filter1=sub2;break;
	    case 3:repeat=sub;break;
	    case 4:rotate=Boolean.parseBoolean(sub);break;
	    case 5:x=Integer.parseInt(sub1);y=Integer.parseInt(sub2);break;
	    case 6:sizex=Integer.parseInt(sub1);sizey=Integer.parseInt(sub2);break;
	    case 7:orizx=Integer.parseInt(sub1);orizy=Integer.parseInt(sub2);break;
	    case 8:offsetx=Integer.parseInt(sub1);offsety=Integer.parseInt(sub2);break;
	    case 9:index=Integer.parseInt(sub);break;
	    case 10:texturegionname=line;break;
	    default:break;
	  }
	   if(i==3)
	   {
		   Assets.TextureNames.add(texturename);
		   Assets.texcount++;
	   }
	   if(i==9)
	   {   if(texturegionname.equals("ghost"))
			 {parseghost(texturename,texturegionname,x,y,sizex,sizey,orizx,orizy,offsetx,offsety,index);
			 }
		   else
		   { texturenameinfo tem=new texturenameinfo(texturename,texturegionname,x,y,sizex,sizey,orizx,orizy,offsetx,offsety,index);
	         if(Assets.TextureRegionNames==null)Assets.TextureRegionNames=new TexturRegionName(); 
	         Assets.TextureRegionNames.add(tem);
		     Assets.texregcount++;
	       }
	   }
	   
	 line=bf.readLine();
	 
	 }
	 
	 } catch (IOException e) {
	 	// TODO Auto-generated catch block
	 	e.printStackTrace();
	 }


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


private static void parseghost(String texturename, String texturegionname, int x, int y, int sizex, int sizey, int orizx, int orizy, int offsetx, int offsety, int index)
{
	
	int ghostheight=sizey/5;
	int ghostwidth=sizex/6;	
	int nghost=26;
	int i=0;
	while(i<nghost)
	{
	 x=x+ (i%6)*ghostwidth;
	 y=y+(i/5)*ghostheight;
	 texturenameinfo tem=new texturenameinfo(texturename,"ghost_"+i,x,y,ghostwidth,ghostheight,ghostwidth,ghostheight,0,0,-1);
     Assets.TextureRegionNames.add(tem);
     Assets.texregcount++;
     i++;
	}
     
	
}

}