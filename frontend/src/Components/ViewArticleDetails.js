import {useEffect, useState} from "react";
import {getArticles, getOneArticle} from "./Client";
import {useNavigate, useParams} from "react-router-dom";
import {Button} from "antd";
import jwt from 'jwt-decode';
import Cookies from 'js-cookie';

const ViewArticleDetails = () => {

    const {id} = useParams();

    const myRoute = useNavigate();

    const [article, setArticle] = useState({
        id:id,
        title:"",
        file:null,
        content:"",
        author:"",
        time:"",
        date:""
    })

    const [title, setTitle] = useState("");
    const [file, setFile] = useState(null);
    const [content, setContent] = useState("");
    const [author, setAuthor] = useState("");
    const [time, setTime] = useState("");
    const [date, setDate] = useState("");


    const fetchOneArticlesFromDatabase = () => {

        getOneArticle(id)
            .then((response) => {
                    setTitle(response.data.title)
                    setFile(response.data.file)
                    setContent(response.data.content)
                    setAuthor(response.data.author)
                    setTime(response.data.time)
                    setDate(response.data.date)
            }
            )
    }

    const UseMyNavigate = useNavigate();

    useEffect(()=> {
        fetchOneArticlesFromDatabase();


    },[])

    const edArticle = (id) => {
        myRoute("/editAnArticle/" + id)
    }

    const token = Cookies.get("myCookie");
    const extractedToken = jwt(token);

    return(
        <>
            <div>
                    <div align="left" style={{marginLeft:'30px'}}>
                        <div align="center">
                            <h2>{article.title}</h2>
                            <img src={process.env.PUBLIC_URL + "/images/" + file} style = {{width:'50%',height:'500px'}}></img><br></br>
                            <strong>{author} | {date} | {time} </strong>{extractedToken.role=="JOURNALIST" && (<Button onClick={() => edArticle(id)} id="edit">EDIT</Button>)}<br></br>
                            <p>{content}</p>
                        </div>
                    </div>
            </div>
        </>
    );
}
export default ViewArticleDetails;