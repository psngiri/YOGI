package yogi.cluster;


public interface ClusterController {
	void process(ClusterCommand command);
	void stop();
	void start();
	void flush();
}
