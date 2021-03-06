/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.schild.jave;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author a.schild
 */
public class EncoderTest {
    
    public EncoderTest() {
    }

    /**
     * Test of getAudioDecoders method, of class Encoder.
     */
    @Test
    public void testGetAudioDecoders() throws Exception {
        System.out.println("getAudioDecoders");
        Encoder instance = new Encoder();
        String[] result = instance.getAudioDecoders();
        assertTrue(result != null && result.length >0, "No audio decoders found");
    }

    /**
     * Test of getAudioEncoders method, of class Encoder.
     */
    @Test
    public void testGetAudioEncoders() throws Exception {
        System.out.println("getAudioEncoders");
        Encoder instance = new Encoder();
        String[] result = instance.getAudioEncoders();
        assertTrue(result != null && result.length >0, "No audio encoders found");
    }

    /**
     * Test of getVideoDecoders method, of class Encoder.
     */
    @Test
    public void testGetVideoDecoders() throws Exception {
        System.out.println("getVideoDecoders");
        Encoder instance = new Encoder();
        String[] result = instance.getVideoDecoders();
        assertTrue(result != null && result.length >0, "No video decoders found");
    }

    /**
     * Test of getVideoEncoders method, of class Encoder.
     */
    @Test
    public void testGetVideoEncoders() throws Exception {
        System.out.println("getVideoEncoders");
        Encoder instance = new Encoder();
        String[] result = instance.getVideoEncoders();
        assertTrue(result != null && result.length >0, "No video enecoders found");
    }

    /**
     * Test of getSupportedEncodingFormats method, of class Encoder.
     */
    @Test
    public void testGetSupportedEncodingFormats() throws Exception {
        System.out.println("getSupportedEncodingFormats");
        Encoder instance = new Encoder();
        String[] result = instance.getSupportedEncodingFormats();
        assertTrue(result != null && result.length >0, "No supported encoding formats found");
    }

    /**
     * Test of getSupportedDecodingFormats method, of class Encoder.
     */
    @Test
    public void testGetSupportedDecodingFormats() throws Exception {
        System.out.println("getSupportedDecodingFormats");
        Encoder instance = new Encoder();
        String[] result = instance.getSupportedDecodingFormats();
        assertTrue(result != null && result.length >0, "No supported decoding formats found");
    }

    /**
     * Test of encode method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testEncodeVideo1() throws Exception {
        System.out.println("encode");
        Logger.getLogger("it.sauronsoftware.jave.FFMPEGExecutor").setLevel(Level.FINEST);
        
        File source = new File("src/test/resources/dance1.avi");
        File target = new File("target/testoutput/target3.3gp");
        if (target.exists())
        {
            target.delete();
        }
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libfaac");
        audio.setBitRate(128000);
        audio.setSamplingRate(44100);
        audio.setChannels(2);
        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(160000);
        video.setFrameRate(15);
        video.setSize(new VideoSize(176, 144));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("3gp");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(source), target, attrs);
        assertTrue( target.exists(), "Output file missing");
    }

    /**
     * Test of encode method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testEncodeVideo2() throws Exception {
        System.out.println("encode");
        Logger.getLogger("it.sauronsoftware.jave.FFMPEGExecutor").setLevel(Level.FINEST);
        File source = new File("src/test/resources/dance1.avi");
        File target = new File("target/testoutput/target4.3gp");
        if (target.exists())
        {
            target.delete();
        }
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libfaac");
        audio.setBitRate(128000);
        audio.setSamplingRate(44100);
        audio.setChannels(2);
        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(160000);
        video.setFrameRate(15);
        video.setSize(new VideoSize(176, 144));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("3gp");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        PListener listener = new PListener();
        encoder.encode(new MultimediaObject(source), target, attrs, listener);
        assertNotNull(listener.getInfo());
        assertTrue( target.exists(), "Output file missing");
    }

    /**
     * Test of encode method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testEncodeVideo3() throws Exception {
        System.out.println("encode");
        Logger.getLogger("it.sauronsoftware.jave.FFMPEGExecutor").setLevel(Level.FINEST);
        File source = new File("src/test/resources/AV36_1.AVI");
        File target = new File("target/testoutput/target4Large.3gp");
        if (target.exists())
        {
            target.delete();
        }
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libfaac");
        audio.setBitRate(128000);
        audio.setSamplingRate(44100);
        audio.setChannels(2);
        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(160000);
        video.setFrameRate(15);
        video.setSize(new VideoSize(176, 144));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("3gp");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        PListener listener = new PListener();
        String message= null;
        String compareTo= "In step: 1 Error in line 10 : <Unknown encoder 'libfaac'>";
        try
        {
            encoder.encode(new MultimediaObject(source), target, attrs, listener);
        }
        catch (EncoderException ex)
        {
            message= ex.getMessage();
        }
        assertEquals(compareTo, message, "Encoding problem not found");
    }
    
    /**
     * Test of encode method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testEncodeVideo4() throws Exception {
        System.out.println("encode");
        Logger.getLogger("it.sauronsoftware.jave.FFMPEGExecutor").setLevel(Level.FINEST);
        File source = new File("src/test/resources/AV36_1.AVI");
        File target = new File("target/testoutput/target4Large2.3gp");
        if (target.exists())
        {
            target.delete();
        }
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("adpcm_ms");
        audio.setBitRate(128000);
        audio.setSamplingRate(44100);
        audio.setChannels(2);
        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(160000);
        video.setFrameRate(15);
        video.setSize(new VideoSize(176, 144));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("3gp");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        PListener listener = new PListener();
        String message= null;
        String compareTo= "codec not currently supported in container";
        try
        {
            encoder.encode(new MultimediaObject(source), target, attrs, listener);
        }
        catch (EncoderException ex)
        {
            message= ex.getMessage();
        }
        assertTrue(message.contains(compareTo), "Encoding problem not found");
    }
    
    /**
     * Test of encode method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testEncodeVideo5() throws Exception {
        System.out.println("encode");
        Logger.getLogger("it.sauronsoftware.jave.FFMPEGExecutor").setLevel(Level.FINEST);
        File source = new File("src/test/resources/AV36_1.AVI");
        File target = new File("target/testoutput/target4Large3.flv");
        if (target.exists())
        {
            target.delete();
        }
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(64000);
        audio.setChannels(1);
        audio.setSamplingRate(22050);
        VideoAttributes video = new VideoAttributes();
        video.setCodec("flv");
        video.setBitRate(160000);
        video.setFrameRate(15);
        video.setSize(new VideoSize(400, 300));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("flv");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        PListener listener = new PListener();
        encoder.encode(new MultimediaObject(source), target, attrs, listener);
        assertNotNull(listener.getInfo());
        assertTrue(target.exists(), "Output file missing");
    }
    
    /**
     * Test of encode method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testEncodeAudio1() throws Exception {
        System.out.println("encode");
        Logger.getLogger("it.sauronsoftware.jave.FFMPEGExecutor").setLevel(Level.FINEST);
        File source = new File("src/test/resources/Alesis-Fusion-Clean-Guitar-C3.wav");
        File target = new File("target/testoutput/Alesis-Fusion-Clean-Guitar-C3.mp3");
        if (target.exists())
        {
            target.delete();
        }
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        PListener listener = new PListener();
        encoder.encode(new MultimediaObject(source), target, attrs, listener);
        assertNotNull(listener.getInfo());
        assertTrue(target.exists(), "Output file missing");
    }

    /**
     * Test of encode method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testEncodeAudio2() throws Exception {
        System.out.println("encode");
        Logger.getLogger("it.sauronsoftware.jave.FFMPEGExecutor").setLevel(Level.FINEST);
        File source = new File("src/test/resources/Alesis-Fusion-Clean-Guitar-C3.wav");
        File target = new File("target/testoutput/Alesis-Fusion-Clean-Guitar-C3.mp3");
        if (target.exists())
        {
            target.delete();
        }
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(42100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        PListener listener = new PListener();
        String message= null;
        String compareTo= "Specified sample rate";
        try
        {
            encoder.encode(new MultimediaObject(source), target, attrs, listener);
        }
        catch (EncoderException ex)
        {
            message= ex.getMessage();
        }
        assertTrue(message.contains(compareTo), "Encoding problem not found");
    }
    
    
    /**
     * Test of encode method, of class Encoder.
     * @throws java.lang.Exception
     */
    @Test
    public void testEncodeAudio3() throws Exception {
        System.out.println("encode");
        Logger.getLogger("it.sauronsoftware.jave.FFMPEGExecutor").setLevel(Level.FINEST);
        File source = new File("src/test/resources/testfile3.wmv");
        File target = new File("target/testoutput/testfile3.mp3");
        if (target.exists())
        {
            target.delete();
        }
        Encoder encoder = new Encoder();
        PListener listener = new PListener();
        String message= null;
        String compareTo= "Specified sample rate";
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        encoder.encode(new MultimediaObject(source), target, attrs);
        assertTrue(target.exists(), "Output file missing");
    }

    protected class PListener implements EncoderProgressListener
    {
        private MultimediaInfo _info= null;
        private final List<String> _messages= new LinkedList<>();
        private final List<Integer> _progress= new LinkedList<>();
        
        @Override
        public void sourceInfo(MultimediaInfo info) {
            _info= info;
        }

        @Override
        public void progress(int permil) {
            _progress.add(permil);
        }

        @Override
        public void message(String message) {
            _messages.add(message);
        }

        /**
         * @return the _info
         */
        public MultimediaInfo getInfo() {
            return _info;
        }

        /**
         * @return the _messages
         */
        public List<String> getMessages() {
            return _messages;
        }

        /**
         * @return the _progress
         */
        public List<Integer> getProgress() {
            return _progress;
        }
        
    }
}
