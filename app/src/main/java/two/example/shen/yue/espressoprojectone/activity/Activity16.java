package two.example.shen.yue.espressoprojectone.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.qihoo360.replugin.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import two.example.shen.yue.espressoprojectone.R;

public class Activity16 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_16);

        findViewById(R.id.btb_skip_plug1).setOnClickListener(e -> skipPlug1());
        findViewById(R.id.btb_install_plug1).setOnClickListener(e -> installPlug1());
        findViewById(R.id.btb_detele_plug1).setOnClickListener(e -> deletePlug1());

    }

    private void deletePlug1(){
        RePlugin.uninstall("plug1");
    }

    private void skipPlug1() {
        if (RePlugin.isPluginInstalled("plug1")) {
            RePlugin.startActivity(Activity16.this,
                    RePlugin.createIntent("plug1",
                            "two.example.shen.yue.espressoprojectone.plug1.MainActivity"));
        } else {
            Toast.makeText(Activity16.this,
                    "You must install plug1 first!", Toast.LENGTH_SHORT).show();
        }
    }


    private void installPlug1() {
        final ProgressDialog pd = ProgressDialog.show(Activity16.this, "Installing...", "Please wait...", true, true);
        // FIXME: 仅用于安装流程演示 2017/7/24
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                simulateInstallExternalPlugin();
                pd.dismiss();
            }
        }, 2000);
    }


    private void simulateInstallExternalPlugin() {
        String demo3Apk= "plug1.apk";
//        String newDemo3Apk= "plug1update.apk";
//        String demo3apkPath = "external" + File.separator + newDemo3Apk;
        String demo3apkPath = "external" + File.separator + demo3Apk;

        // 文件是否已经存在？直接删除重来
        String pluginFilePath = getFilesDir().getAbsolutePath() + File.separator + demo3Apk;
        File pluginFile = new File(pluginFilePath);
        if (pluginFile.exists()) {
            FileUtils.deleteQuietly(pluginFile);
        }

        // 开始复制
        copyAssetsFileToAppFiles(demo3apkPath, demo3Apk);
        PluginInfo info = null;
        if (pluginFile.exists()) {
            info = RePlugin.install(pluginFilePath);
        }

        if (info != null) {
//            RePlugin.startActivity(Activity16.this, RePlugin.createIntent(info.getName(), "com.qihoo360.replugin.sample.demo3.MainActivity"));
            Toast.makeText(Activity16.this, "install external plugin success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Activity16.this, "install external plugin failed", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 从assets目录中复制某文件内容
     *  @param  assetFileName assets目录下的Apk源文件路径
     *  @param  newFileName 复制到/data/data/package_name/files/目录下文件名
     */
    private void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = this.getAssets().open(assetFileName);
            fos = this.openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
