package second;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.Scene;

import first.J3DLoader;

/**
 * @author zhouyun 2014-6-15ÉÏÎç9:40:23
 */
public class Addmodel {

	public TransformGroup testAddmodel(Body b) {
			
		J3DLoader loader = new J3DLoader();
		Scene scene1 = loader.loadObj(b.objName);
		
		TransformGroup objTransG=new TransformGroup();
		Transform3D pos = new Transform3D();

        pos.setTranslation(new Vector3f(b.getCore_pos().x3d,b.getCore_pos().y3d,b.getCore_pos().z3d));
    
        objTransG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objTransG.setTransform(pos);
        objTransG.addChild(scene1.getSceneGroup());
		
		return objTransG;
	}
}
