package com.zgj.min3d.tools;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
public class MediaTransformer extends Thread {

	@Value("${resource.dir}")
	private String resourceDir;

	private BlockingQueue<Source> queue = new ArrayBlockingQueue<Source>(30000);

	private MediaTransformer() {

		new Thread(this).start();
	}

	public void produce(String path, Short type) throws InterruptedException {
		if (!queue.offer(new Source(path, type))) {
			System.out.println("VideoTransformer length = " + queue.size());
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				transformProc(queue.take());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void transformProc(Source source) {
		if (source.getType() == 1) {
			transVideoToFlv(source.getPath());
			transVideoToPng(source.getPath());
		}

		if (source.getType() == 2) {
			if (!source.getPath().toLowerCase().endsWith(".svg")) {
				transImageToPng(source.getPath());
			} else {
				transSvgToPng(source.getPath());
			}
		}
	}

	private void transVideoToFlv(String source) {
		try {
			File file = new File(source);
			if (!file.exists()) {
				return;
			}
			String targetVideo = file.getParentFile().getPath() + File.separator + file.getName() + ".flv";
			File targetFile = new File(targetVideo);
			if (targetFile.exists()) {
				targetFile.delete();
			}
			String tool = resourceDir + File.separator + "tools" + File.separator + "ffmpeg.exe";
			List<String> commend = new java.util.ArrayList<String>();
			commend.add(tool);
			commend.add("-i");
			commend.add(source);
			commend.add("-ab");
			commend.add("64");
			commend.add("-ac");
			commend.add("2");
			commend.add("-ar");
			commend.add("22050");
			commend.add("-r");
			commend.add("15");
			commend.add("-acodec");
			commend.add("libmp3lame");
			commend.add("-ss");
			commend.add("2");
			commend.add("-t");
			commend.add("10");
			commend.add("-b");
			commend.add("512");
			commend.add("-qscale");
			commend.add("6");
			commend.add("-y");
			commend.add(targetVideo);

			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process process = builder.start();
			process.waitFor();
			process.destroy();
			builder.directory();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void transVideoToPng(String source) {
		try {
			File file = new File(source);
			if (!file.exists()) {
				return;
			}
			String targetImage = file.getParentFile().getPath() + File.separator + file.getName() + ".png";
			File targetFile = new File(targetImage);
			if (targetFile.exists()) {
				targetFile.delete();
			}
			String tool = resourceDir + File.separator + "tools" + File.separator + "ffmpeg.exe";
			List<String> commend = new java.util.ArrayList<String>();
			commend.add(tool);
			commend.add("-i");
			commend.add(source);
			commend.add("-y");
			commend.add("-f");
			commend.add("image2");
			commend.add("-ss");
			commend.add("2");
			commend.add("-t");
			commend.add("0.001");
			commend.add(targetImage);

			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process process = builder.start();
			process.waitFor();
			process.destroy();
			builder.directory();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void transImageToPng(String source) {
		try {
			File file = new File(source);
			if (!file.exists()) {
				return;
			}

			String targetImage = file.getParentFile().getPath() + File.separator + file.getName() + ".png";
			File targetFile = new File(targetImage);
			if (targetFile.exists()) {
				targetFile.delete();
			}
			String tool = resourceDir + File.separator + "tools" + File.separator + "ffmpeg.exe";
			List<String> commend = new java.util.ArrayList<String>();
			commend.add(tool);
			commend.add("-i");
			commend.add(source);

			InputStream is = new FileInputStream(file);
			BufferedImage src = ImageIO.read(is);
			Integer sourceWidth = src.getWidth(null);
			Integer sourceHeight = src.getHeight(null);
			is.close();

			commend.add("-s");
			commend.add((sourceWidth / 2) + "x" + (sourceHeight / 2));

			commend.add(targetImage);

			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process process = builder.start();
			process.waitFor();
			process.destroy();
			builder.directory();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void transSvgToPng(String source) {
		try {
			File svg = new File(source);
			if (!svg.exists()) {
				return;
			}

			String targetImage = svg.getParentFile().getPath() + File.separator + svg.getName() + ".png";
			File targetFile = new File(targetImage);
			if (targetFile.exists()) {
				targetFile.delete();
			}

			InputStream in = new FileInputStream(svg);
			OutputStream out = new FileOutputStream(targetFile);
			out = new BufferedOutputStream(out);

			Transcoder tr = new PNGTranscoder();

			TranscoderInput input = new TranscoderInput(in);
			try {
				TranscoderOutput output = new TranscoderOutput(out);
				tr.transcode(input, output);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class Source {
		String path = null;
		Short type = null;

		public Source(String path, Short type) {
			this.path = path;
			this.type = type;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public Short getType() {
			return type;
		}

		public void setType(Short type) {
			this.type = type;
		}

	}
}
