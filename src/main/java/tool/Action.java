package tool;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(
		maxFileSize = 104857600,  // 最大ファイルサイズ: 100MB
	    maxRequestSize = 524288000,  // 最大リクエストサイズ: 500MB
	    fileSizeThreshold = 0  // ファイルのサイズの閾値（0は即時保存）
	)
public abstract class Action {
	public abstract String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception;
}
