import {Button, Input} from "antd";
import TextArea from "antd/es/input/TextArea";
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {createArticle, editAnArticle, getOneArticle} from "./Client";
import jwt from 'jwt-decode'
import Cookies from 'js-cookie'

const EditArticle = () => {

    const decoded = jwt(Cookies.get("myCookie"))

    const {id} = useParams();
    const [article, setArticle] = useState({
        id:id,
        title:"",
        file:null,
        content:""
    });

    const [title, setTitle] = useState("");
    const [file, setFile] = useState(null || "");
    const [content, setContent] = useState("");

    const formData = new FormData();
    const updateArticle = () => {

        formData.append("file", file);
        formData.append("title",title)
        formData.append("content",content)

        console.log(formData.get("title"));
        console.log(formData.get("content"));
        console.log(formData.get("file"));

            editAnArticle(id, formData.get("title"), formData.get("file"), formData.get("content") )
                .then(response => console.log(response))
                .catch(error => console.log(error));


    }

    const getPrevArticleContent = () => {
        getOneArticle(id)
            .then((response) => {
                setTitle(response.data.title)
                    setFile(response.data.file)
                    setContent(response.data.content)
            })
            .then(error => console.log(error));

    }

    useEffect(()=>{
        getPrevArticleContent();

    },[]);

    return(
        <>
            <form>
                <Input name = "title" placeholder="Enter article title" value={title} onChange={(e) => setTitle(e.target.value)}></Input><br></br>
                <Input name = "file" type="file" style={{width:'300px',height:'50px'}} onChange={(e) => setFile(e.target.files[0])}></Input><br></br><br></br>
                <TextArea name ="content" style={{width:'300px',height:'200px'}} value={content} onChange={(e)=>setContent(e.target.value)}></TextArea><br></br><br></br>
                <Button onClick={updateArticle}>UPDATE</Button>
            </form>


        </>
    );
}

export default EditArticle;