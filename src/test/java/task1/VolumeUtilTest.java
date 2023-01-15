package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.task1.AudioManager;
import org.task1.RINGER_MODE;
import org.task1.VolumeUtil;

import static org.mockito.Mockito.*;

public class VolumeUtilTest {

    private AudioManager audioManager;

    @BeforeEach
    void setUp(){
        audioManager=mock(AudioManager.class);
    }
    @Test
    void testNormalRingerIsMaximized(){
        //1. AudioManager--->mock
        //2. configure AudioManager to return RINGER_MODE_NORMAL if getRingerMode is called
        when(audioManager.getRingerMode()).thenReturn(RINGER_MODE.RINGER_MODE_NORMAL);
        //3. configure AM to return 100 if getStreamMaxVolume is called
        when(audioManager.getStreamMaxVolume()).thenReturn(100);
        //4. call volumeUtil.maximizeVolume with AM
        VolumeUtil.maximizeVolume(audioManager);
        //5. verify that setStreamVolume(100) was called on AM
        verify(audioManager).setStreamVolume(100);
        verify(audioManager).getRingerMode();
        verify(audioManager).getStreamMaxVolume();
    }

    @Test
    void testSilentRingerIsNotDisturbed(){
        //1. AM--->mock
        //2. configure AM to return RINGER_MODE_SILENT if getRingerMode is called
        when(audioManager.getRingerMode()).thenReturn(RINGER_MODE.RINGER_MODE_SILENT);
        //3. call volumeUtil.maximizeVolume with AM
        VolumeUtil.maximizeVolume(audioManager);
        //4. verify that getRingerMode() is called on the mock
        verify(audioManager).getRingerMode();
        //5. ensure that nothing more was called
        verify(audioManager,never()).getStreamMaxVolume();
        verify(audioManager,never()).setStreamVolume(anyInt());
        //zakomentarisana 9. linija u volumeutil namerno
        //moze i ovo
        //verifyNoMoreInteractions(audioManager);
    }
}
