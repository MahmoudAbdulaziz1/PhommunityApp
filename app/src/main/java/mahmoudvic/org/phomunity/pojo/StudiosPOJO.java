package mahmoudvic.org.phomunity.pojo;

import java.util.List;

public class StudiosPOJO {

    private List<StudioOptionsPOJO> options;
    private List<ImagePOJO> slides;
    private List<VideoStudioPOJO> video;


    //    public static void main(String[] args) {
    //        System.out.println(" the Runnnnnn ");
    //        String videoURL = "{\"title\":\"CPC Studio\",\"caption\":\"studio for rent \",\"description\":\"Studio costs 250 pounds per hour with 3 head lights. makeup area . dressing room and Technical Support \",\"youtube_video_url\":\"https:\\/\\/www.youtube.com\\/watch?v=z0kL9Y21Pus\"}";
    //        System.out.println(" URL ::::"+ VideoUtil.extractVideoIdFromUrl(videoURL).substring(0,11));
    ////        Log.d("VideoURL",extractVideoIdFromUrl(videoURL));
    //    }
    public StudiosPOJO(List<StudioOptionsPOJO> options, List<ImagePOJO> slides, List<VideoStudioPOJO> video) {
        this.options = options;
        this.slides = slides;
        this.video = video;
    }

    public List<StudioOptionsPOJO> getOptions() {
        return options;
    }

    public void setOptions(List<StudioOptionsPOJO> options) {
        this.options = options;
    }

    public List<ImagePOJO> getSlides() {
        return slides;
    }

    public void setSlides(List<ImagePOJO> slides) {
        this.slides = slides;
    }

    public List<VideoStudioPOJO> getVideo() {
        return video;
    }

    public void setVideo(List<VideoStudioPOJO> video) {
        this.video = video;
    }
}