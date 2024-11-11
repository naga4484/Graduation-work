package bean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FileUploadForm extends ActionForm {
    private FormFile submissionsFile;  // ファイルのフィールド

    public FormFile getSubmissionsFile() {
        return submissionsFile;
    }

    public void setSubmissionsFile(FormFile submissionsFile) {
        this.submissionsFile = submissionsFile;
    }
}