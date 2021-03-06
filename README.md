# JAVE2

The JAVE2 (Java Audio Video Encoder) library is Java wrapper on the ffmpeg
project. Developers can take take advantage of JAVE2 to transcode audio 
and video files from a format to another. In example you can transcode
an **AVI** file to a MPEG one, you can change a **DivX** video stream into a
(youtube like) Flash **FLV** one, you can convert a **WAV** audio file to a **MP3** or a
**Ogg Vorbis** one, you can separate and transcode audio and video tracks, you can
resize videos, changing their sizes and proportions and so on.
Many other formats, containers and operations are supported by JAVE2.

## Supported Operating Systems + Requirements 

1)JAVE requires a Java 8
>JAVE can also be easily ported to other OS and hardware configurations, 
see the JAVE manual for details.

| Operating System | Windows x32,x64 | MacOS x32,x64 | Linux x32,x64 |
| ------- | :-----: | :-: | :-----: |
| Supported? | YES | YES  | YES  |


## Usage Example
For the documentation, please have a look at the project wiki pages [**here**](https://github.com/a-schild/jave2/wiki)

or at this file [**DefaultFFMPEGLocatorTest.java**](https://github.com/a-schild/jave2/blob/master/jave-example/src/main/java/ws/schild/jave/example/DefaultFFMPEGLocatorTest.java)


Maven Repository URL -> https://mvnrepository.com/artifact/ws.schild/jave-all-deps

### Use with Maven

It includes all binaries for the supported platforms

<!-- https://mvnrepository.com/artifact/ws.schild/jave-all-deps -->
``` XML
<dependency>
 <groupId>ws.schild</groupId>
 <artifactId>jave-all-deps</artifactId>
 <version>2.4.2</version>
</dependency>
```

### Use with Gradle

It includes all binaries for the supported platforms

``` XML
compile group: 'ws.schild', name: 'jave-all-deps', version: '2.4.2'
```

### Main Components of Jave2
Jave2 consists of two main components:
1. The `jave-core` dependency, which includes all the java code, which is platform independent
2. The `jave-native-<platform>` dependencies, which include the binary executables per platform

There exists a jave-all-deps project, which includes core and all windows und linux binaries.

---
## Examples

#### Converting any audio to mp3

``` JAVA
   try {                                                            
	File source = new File("file path");		                 
	File target = new File("file path);                          
                                                                 
       //Audio Attributes                                               
	AudioAttributes audio = new AudioAttributes();               
	audio.setCodec("libmp3lame");                                
	audio.setBitRate(128000);                                    
	audio.setChannels(2);                                        
	audio.setSamplingRate(44100);                                
	                                                             
	//Encoding attributes                                        
	EncodingAttributes attrs = new EncodingAttributes();         
	attrs.setFormat("mp3");                                      
	attrs.setAudioAttributes(audio);                             
	                                                             
	//Encode                                                     
	Encoder encoder = new Encoder();                             
	encoder.encode(new MultimediaObject(source), target, attrs); 
                                                                 
   } catch (Exception ex) {                                         
	ex.printStackTrace();                                        
	succeeded = false;                                           
   }                

```
     
## More advanced examples    

#### Converting any audio to mp3 with a progress listener

``` JAVA 
   ConvertProgressListener listener = new ConvertProgressListener();

   try {                                                            
	File source = new File("file path");		                 
	File target = new File("file path);                          
                                                                 
       //Audio Attributes                                               
	AudioAttributes audio = new AudioAttributes();               
	audio.setCodec("libmp3lame");                                
	audio.setBitRate(128000);                                    
	audio.setChannels(2);                                        
	audio.setSamplingRate(44100);                                
	                                                             
	//Encoding attributes                                        
	EncodingAttributes attrs = new EncodingAttributes();         
	attrs.setFormat("mp3");                                      
	attrs.setAudioAttributes(audio);                             
	                                                             
	//Encode                                                     
	Encoder encoder = new Encoder();                             
	encoder.encode(new MultimediaObject(source), target, attrs,listener); 
                                                                 
   } catch (Exception ex) {                                         
	ex.printStackTrace();                                        
	succeeded = false;                                           
   }                                                                


  public class ConvertProgressListener implements EncoderProgressListener {
	                                                                     
     public ConvertProgressListener() {   
        //code
     }                                                                    
	                                                                     
     public void message(String m) {                                      
       //code                                                              
     }                                                                    
	                                                                     
     public void progress(int p) {                                        
		                                                                 
       //Find %100 progress                                              
	double progress = p / 1000.00;                                    
	System.out.println(progress);                                     
                                                                         
     }                                                                    
	                                                                     
      public void sourceInfo(MultimediaInfo m) {                           
         //code                                                          
      }                                                                    
   }                                                                                                                                                
```

---

## License

JAVE2 is Free Software and it is licensed under GPL 2 (you will find a copy of
the license bundled into the downloadable software distribution).


## Feedback

You can send comments to andre@schild.ws
For bug reports use the github site https://github.com/a-schild/jave2/issues

## Changelog
- 2.4.2 Enhanced output parsing when using copy operator for streams
        Refactoring of outpout analyzer in own class for simpler unit tests
- 2.4.1 Allow conversion of "corrupt" input files, as generated by some softwares
- 2.4.0 Renaming packages to ws.schild.jave for publishing in maven central
        First version released via maven central

## Credits

Jave is based on the jave version from Carlo Pelliccia
The original project page with source code can be found here:

http://www.sauronsoftware.it/projects/jave/
