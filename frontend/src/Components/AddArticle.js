import {Button, DatePicker, Image, Input, TimePicker} from "antd";
import TextArea from "antd/es/input/TextArea";
import {createArticle} from "./Client";
import {useState} from "react";

const AddArticle = () => {

    const [title, setTitle] = useState("");
    const [imageData, setImageData] = useState(null || "");
    const [content, setContent] = useState("");

    const [article, setArticle] = useState({
        title:"",
        file:null,
        content:""
    });

    const addArticle = () => {
        const formData = new FormData();
        formData.append("file", imageData);
        formData.append("title",title)
        formData.append("content",content)

        createArticle(formData.get("title"), formData.get("file"), formData.get("content"))
            .then(response => console.log(response))
            .catch(error => console.log(error));
    }

    return(
     <>
         <form>
         <Input name = "title" placeholder="Enter article title" value={title} onChange={(e) => setTitle(e.target.value)}></Input><br></br>
         <Input name = "file" type="file" style={{width:'300px',height:'50px'}} onChange={(e) => setImageData(e.target.files[0])}></Input><br></br><br></br>
         <TextArea name ="content" style={{width:'300px',height:'200px'}} value={content} onChange={(e)=>setContent(e.target.value)}></TextArea><br></br><br></br>
         <Button onClick={addArticle}>ADD</Button>

         </form>

     </>
    )

}
export default AddArticle;