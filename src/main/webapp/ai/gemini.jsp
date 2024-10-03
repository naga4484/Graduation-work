<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h1>AI(Gemini)の使用について</h1>
<p>多分、AI君が間違わなければちゃんと返答ができると思います…</p>

<p>質問：<br>
<input type="text" id="ai-request"></p>
<p><input type="button" id="ai-request-button" value="質問の送信"></p>


<div id="ai-response">
</div>

<!-- ここらへんはAI(Gemini君)の機能欄 -->
<script type="importmap">
      {
        "imports": {
          "@google/generative-ai": "https://esm.run/@google/generative-ai"
        }
      }
</script>
<script type="module">
      import { GoogleGenerativeAI } from "@google/generative-ai";

      // Fetch your API_KEY
      const API_KEY = "AIzaSyBg0eT_MiLkm_MFez5hwE0upVSyQe9y33w";
      // Reminder: This should only be for local testing

      // Access your API key (see "Set up your API key" above)
      const genAI = new GoogleGenerativeAI(API_KEY);

      // ...
	  let ai_response = document.getElementById('ai-response');

      async function run(ai_request) {
      // The Gemini 1.5 models are versatile and work with both text-only and multimodal prompts
      let model = genAI.getGenerativeModel({ model: "gemini-1.5-flash"});

      let result = await model.generateContent(ai_request);
      let response = await result.response;
      let text = response.text();
	  console.log(text);
      let htmlContent = text.replace(/\*\*(.*?)\*\*/g, '<br><strong>$1</strong><br>');
	  let fullhtmlContent = htmlContent.replace(/\*/g, '<br>');
      console.log(fullhtmlContent);
	  

	  ai_response.innerHTML=  fullhtmlContent;
      }

	  const ai_button = document.getElementById('ai-request-button');
	  let ai_request = "日本語での回答でお願いします";

	  ai_button.addEventListener('click' ,function(e){
		
	  ai_request = document.getElementById('ai-request').value;
	  run(ai_request);
		
	  });
</script>
    
 
 <%@include file="../footer.jsp"  %>