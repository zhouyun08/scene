package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;

/**
 *@author zhouyun
 *2014-6-13上午10:40:35
 */
public class J3DLoader {

    private static java.net.URL texImage = null; 
    
    /**
     * 载入obj模型
     * @param arg0 String 模型文件名
     * @return Scene
     */
    public Scene loadObj(String arg0)
    {
        int flags = ObjectFile.RESIZE;
        ObjectFile f = new ObjectFile(flags,(float)(49.0 * Math.PI / 180.0));
        // 创建场景叶节点
        Scene s = null;
        try {
            s = f.load(arg0);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IncorrectFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParsingErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }
    
    /**
     * 载入纹理图片
     * @param args String 图片文件名
     * @return URL
     */
    public URL loadTexture(String args)
    {
        File f = new File(args);
        try {
            texImage = f.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return texImage;
    }
}
