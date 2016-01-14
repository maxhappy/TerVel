

	package inductionlabs.tervelhd;

	import java.util.ArrayList;
    import java.util.List;


	public class TexturRegionName  
	{
		public static List<String> texturename = new ArrayList<String>(1000);
		public static List<String> texturegionname= new ArrayList<String>(1000);
		public static List<Integer> x= new ArrayList<Integer>(1000); 
		public static List<Integer> y= new ArrayList<Integer>(1000); 
		public static List<Integer> sizex= new ArrayList<Integer>(1000); 
		public static List<Integer> sizey= new ArrayList<Integer>(1000); 
		public static List<Integer> orizx= new ArrayList<Integer>(1000); 
		public static List<Integer> orizy= new ArrayList<Integer>(1000);
		public static List<Integer> offsetx= new ArrayList<Integer>(1000); 
		public static List<Integer> offsety= new ArrayList<Integer>(1000); 
		public static List<Integer> index= new ArrayList<Integer>(1000);
		texturenameinfo temp;
		
		//public TexturRegionName(String texturename, String texturegionname,int x, int y, int sizex, int sizey, int orizx, int orizy,int offsetx, int offsety, int index) 
		//{
		//	temp=new texturenameinfo(texturename, texturegionname, x,  y, sizex,  sizey, orizx, orizy, offsetx,  offsety,  index);	
    	//	}

		
		public boolean add(texturenameinfo ob) 
		{
			TexturRegionName.texturename.add(ob.texturename); 
			TexturRegionName.texturegionname.add(ob.texturegionname);
			TexturRegionName.x.add(ob.x); 
			TexturRegionName.y.add(ob.y); 
			TexturRegionName.sizex.add(ob.sizex); 
			TexturRegionName.sizey.add(ob.sizey); 
			TexturRegionName.orizx.add(ob.orizx); 
			TexturRegionName.orizy.add(ob.offsety);
			TexturRegionName.offsetx.add(ob.offsetx); 
			TexturRegionName.offsety.add(ob.offsety); 
			TexturRegionName.index.add(ob.index);
			return true;
		}

		public void add(int location,texturenameinfo ob) 
		{TexturRegionName.texturename.add(location,ob.texturegionname); 
		TexturRegionName.texturegionname.add(location,ob.texturegionname);
		TexturRegionName.x.add(location,ob.x); 
		TexturRegionName.y.add(location,ob.y); 
		TexturRegionName.sizex.add(location,ob.sizex); 
		TexturRegionName.sizey.add(location,ob.sizey); 
		TexturRegionName.orizx.add(location,ob.orizx); 
		TexturRegionName.orizy.add(location,ob.offsety);
		TexturRegionName.offsetx.add(location,ob.offsetx); 
		TexturRegionName.offsety.add(location,ob.offsety); 
		TexturRegionName.index.add(location,ob.index);
			
		}

		public void clear() 
		{
			// TODO Auto-generated method stub
			
		}

		public Object get(int location)
		{
			// TODO Auto-generated method stub
			return null;
		}


		public int indexOf(Object object)
		{
			// TODO Auto-generated method stub
			return 0;
		}



		public int lastIndexOf(Object object) 
		{
			return 0;
		}

		public Object remove(int location) 
		{
			// TODO Auto-generated method stub
			return null;
		}

		public int size() 
		{return texturegionname.size();
		}

	 
		
		 
		
		
	}
