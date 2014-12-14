package second;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.net.URL;

import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import first.J3DLoader;
import first.Vertex;

/**
 *@author zhouyun
 *2014-6-14下午9:43:29
 */
public class Show extends Applet{
    
	 private SimpleUniverse universe ; 
	    private Canvas3D canvas; 
	    private BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1000.0);
	    TransformGroup objTransG = null;
	    
	    public void Main()
	    {
	    	
	    	
	    }
	    
	    public void setupView()
	    {
	        /** Add some view related things to view branch side of scene graph */ 
	        // add mouse interaction to the ViewingPlatform    
	        OrbitBehavior orbit = new OrbitBehavior(canvas, OrbitBehavior.REVERSE_ALL|OrbitBehavior.STOP_ZOOM); 
	        orbit.setSchedulingBounds(bounds);     
	        ViewingPlatform viewingPlatform = universe.getViewingPlatform();     
	        
	        Vector3d viewPoint=new Vector3d(0.0,0.0,8.0);
	        Transform3D t=new Transform3D();
	        t.set(viewPoint);
	        viewingPlatform.getViewPlatformTransform().setTransform(t);
	        viewingPlatform.setViewPlatformBehavior(orbit);  
	    } 
	    
	    public void init()
	    {
	        setLayout(new BorderLayout());
	        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
	        canvas = new Canvas3D(gc);
	        add("Center",canvas);
	        
	        // Create a simple scene and attach it to the virtual universe
	        universe = new SimpleUniverse(canvas);           
	        
	        // 定义观察点，可用鼠标进行缩放、旋转
	        setupView();   
	        
	        // 定义场景的根结点
	        BranchGroup objRoot = new BranchGroup();	        
	           
	        Color3f directionalColor=new Color3f(1.0f,1.0f,0.9f);
			Vector3f vec=new Vector3f(4.0f,-7.0f,-12.0f);
			DirectionalLight directionalLight=new DirectionalLight(directionalColor,vec);
			directionalLight.setInfluencingBounds(bounds);
			objRoot.addChild(directionalLight);
	              	
			   J3DLoader loader = new J3DLoader();
		        
			   	Body[] b = new Body[20];
			   	TransformGroup[] objTransG=new TransformGroup[20];
			   	
			   	int objNumber=4;					//一共objNumber个模型
			   	for(int i=0;i<objNumber;i++)
			   	{
			   		b[i]=new Body();
			   		objTransG[i]=new TransformGroup();
			   	}
			   	
			   	b[0].testBody("桌子.obj");
				b[1].testBody("沙发.obj");
				b[2].testBody("风扇.obj");
				b[3].testBody("老鼠.obj");
				
				BodyProcesser bp = new BodyProcesser();				
				bp.test1(b[0], b[1], "left");
				bp.test1(b[0], b[2], "up");
				bp.test1(b[1], b[3], "back");
				
				
				Addmodel addM=new Addmodel();
				for(int i=0;i<objNumber;i++)
				{										
					objTransG[i]=addM.testAddmodel(b[i]);
					objRoot.addChild(objTransG[i]);
				} 

	        
	     // 载入纹理  墙壁
	        URL texImagearound = loader.loadTexture("wall.jpg");
	        // 画了一个大矩形，作为墙壁
	        Group grouparound = this.createSceneBackGroundaround(texImagearound);
	        objRoot.addChild(grouparound);
	        
	        // 载入纹理    地面
	        URL texImage = loader.loadTexture("floor.jpg");
	        // 画了一个大矩形，作为地板
	        Group group = this.createSceneBackGround(texImage); 
	        objRoot.addChild(group);
	        
	        universe.addBranchGraph(objRoot);
	    }
	    
	    public void destroy() 
	    {    
	        universe.removeAllLocales();    
	    } 
	    
	    /**
	     * @param args
	     */
	    public static void main(String[] args) {
	        new MainFrame(new Show(), 600, 600);
	    }

	    
	    public Group createSceneBackGround(URL texImage)
	    {
	        return createGeometry(Texture.MULTI_LEVEL_POINT , -2.1f, texImage);
	    }
	    
	    public Group createGeometry(int filter, float y, URL texImage)
	    {
	        Group topNode = new Group();
	        Appearance appearance = new Appearance(); 
	        TextureLoader tex = new TextureLoader(texImage, TextureLoader.GENERATE_MIPMAP , this);
	        Texture texture = tex.getTexture();
	        
	        texture.setMinFilter(filter);
	        appearance.setTexture(texture);   
	        TextureAttributes texAttr = new TextureAttributes();  
	        texAttr.setTextureMode(TextureAttributes.MODULATE);  
	        appearance.setTextureAttributes(texAttr); 
	        
	        //TODO light
	        Transform3D pos2 = new Transform3D();
	        pos2.setTranslation(new Vector3f(0f,y,0f));   
	        objTransG = new TransformGroup(); 
	        objTransG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	        objTransG.setTransform(pos2);
	        
	        
	        Box box = new Box(8f,0.1f,8f,Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,appearance);
	        objTransG.addChild(box);
	        topNode.addChild(objTransG);
	        return topNode;
	    }
	    
	    public Group createSceneBackGroundaround(URL texImage)
	    {
	        return createGeometryaround(Texture.MULTI_LEVEL_POINT , -6.1f, texImage);
	    }
	    
	    public Group createGeometryaround(int filter, float z, URL texImage)
	    {
	        Group topNode = new Group();
	        Appearance appearance = new Appearance(); 
	        TextureLoader tex = new TextureLoader(texImage, TextureLoader.GENERATE_MIPMAP , this);
	        Texture texture = tex.getTexture();
	        
	        texture.setMinFilter(filter);
	        appearance.setTexture(texture);   
	        TextureAttributes texAttr = new TextureAttributes();  
	        texAttr.setTextureMode(TextureAttributes.MODULATE);  
	        appearance.setTextureAttributes(texAttr); 
	        
	        //TODO light
	        Transform3D pos2 = new Transform3D();
	        pos2.setTranslation(new Vector3f(0f,0f,z));   
	        objTransG = new TransformGroup(); 
	        objTransG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	        objTransG.setTransform(pos2);
	        
	        
	        Box box = new Box(8f,8f,0.1f,Primitive.GENERATE_NORMALS|Primitive.GENERATE_TEXTURE_COORDS,appearance);
	        objTransG.addChild(box);
	        topNode.addChild(objTransG);
	        return topNode;
	    }
	    
	}