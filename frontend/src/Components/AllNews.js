import {useEffect, useState} from "react";
import {getAllArticles, getArticles, getEveryArticles, getOneArticle} from "./Client";
import Navbar from "./Navbar";
import Footer from "./Footer";
import {useNavigate} from "react-router-dom";
import jwt from 'jwt-decode';
import Cookies from 'js-cookie';


const AllNews = () => {

    const navigate = useNavigate();

    const [article, setArticle] = useState([]);

    const [fullMessage, setFullMessage] = useState(false);

    const handleRead = (id) => {
        navigate("/ViewArticleDetails/" + id);

    }

    const token = Cookies.get("myCookie");
    const extractedToken = jwt(token);

    const readAll = ()=>{
      if (extractedToken.role == "JOURNALIST") {
          getEveryArticles()
              .then(response => setArticle(response.data))
      }
        else
            console.log("you cannot view all articles")


    }

    useEffect(()=>{
       readAll()
    },[])


    return (
     <>

         <Navbar></Navbar>


         <div align="left" style={{marginTop:'0px'}}>
             {article.map((article, index) => (
                 <div key={index} style ={{float:'left',margin:'50px',boxShadow:'0px 0px 1px 1px',border:'none',
                     width:'330px', height:'400px',padding:'20px'
                 }}>

                     <h2 style={{fontFamily:'Bahnschrift Light',color:'green'}}>{article.title}</h2>
                     <img src= {process.env.PUBLIC_URL + '/images/' + article.file} style={{width:'270px',height:'200px'}} onError={(e)=>{console.error('Error:',e)}}/>
                     <p style ={{width:'200px',textAlign:'left'}} >{fullMessage==true? article.content : article.content.substring(0,50)}</p><button style ={{background:'none',border:'none',color:'blue'}} onClick={() => handleRead(article.id)}>ReadMore</button>
                 </div>
             ))}
         </div>
         <br></br>
         {/*<Footer></Footer>*/}
     </>
    )
}
export default AllNews;