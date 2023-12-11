import {useEffect, useState} from "react";
import {deleteSingleArticle, getArticles, getNineArticles} from "./Client";
import {Button} from "antd";
import {useNavigate} from "react-router-dom";
import jwt from 'jwt-decode';
import Cookies from 'js-cookie';

const GetArticlesForHome = () => {

    const [articles, setArticles] = useState([{
        file:null,
        title:"",
        date:"",
        content:"",
        author:""
    }]);

    const [arts, setArts] = useState([]);

    const fetchAllArticlesFromDatabase = () => {
        getNineArticles()
            .then(response => response.json())
            .then(data => setArticles(data))
            .catch(error => console.log(error));
    }

    const UseMyNavigate = useNavigate();

    useEffect(()=> {
        fetchAllArticlesFromDatabase();

    },[])

    const [fullMessage, setFullMessage] = useState(false);

    const handleRead = (id) => {
        // setFullMessage(true);
        UseMyNavigate("/ViewArticleDetails/" + id);
        fetchAllArticlesFromDatabase();
    }

    const deleteAnArticle = (id) => {
        deleteSingleArticle(id)
            .then((response) => {
                if (articles) {
                    setArticles(prevState => {
                        const updatedArticle = prevState.filter(article => article.id != id)
                            return [...updatedArticle, articles]
                    })
                }
                }
            )
    }

    const token = Cookies.get("myCookie");
    const extractedToken = jwt(token);

    return (
        <>
            <div>
            <img style={{opacity:'0.5',width:'90%',alignContent:'center',marginTop:'20px',height:'500px'}} src={process.env.PUBLIC_URL + '/images/' + "4.jpg"}></img>
                {/*<div style={{position:'absolute',top:'200px',marginLeft:'150px',padding:'0px',alignContent:'left'}}>*/}
                    <h1 style={{fontSize:'70px',color:'green',marginTop:'100px',fontFamily:'Bahnschrift Light',position:'absolute',top:'200px',marginLeft:'150px'}}>WELCOME</h1>
                    <p style={{marginTop:'100px',position:'absolute',top:'300px',marginLeft:'150px',fontFamily:'Ebrima',
                    fontSize:'20px'
                    }}>Your number 1 news plug</p>
                <div style={{position:'absolute',top:'450px',marginLeft:'150px',marginTop:'100px'}}>
                    <Button style={{backgroundColor:'green',color:'white',width:'300px',
                        height:'50px',borderRadius:'20px'}}>GET STARTED ></Button>
                </div>
            </div>

            <h1 style={{color:'green',fontSize:'50'}}>LATEST NEWS</h1>
            <hr style={{width:'40px',color:'green'}}></hr>

            <div align="left" style={{marginTop:'0px'}}>
                {articles.map((article, index) => (
                    <div key={index} style ={{float:'left',margin:'30px',boxShadow:'0px 0px 1px 1px',border:'none',
                        width:'380px', height:'370px',padding:'20px',borderColor:'grey'
                    }}>

                        <img src= {process.env.PUBLIC_URL + '/images/' + article.file} style={{width:'100%',height:'200px'}}/>
                        <strong style={{fontFamily:'Bahnschrift Light',color:'green',fontStyle:'bold',fontSize:'20px'}}>{article.title}</strong>
                        <h6>{article.date}</h6>
                        <p style ={{width:'100%',textAlign:'left'}} >{fullMessage==true? article.content : article.content.substring(0,50)}</p><button style ={{background:'none',border:'none',color:'blue'}} onClick={() => handleRead(article.id)}>ReadMore</button>
                        by <strong>{article.author}</strong>
                        {extractedToken.role == "JOURNALIST" && <Button style={{backgroundColor:'transparent',color:'black',float:'right'}} onClick={() => deleteAnArticle(article.id)}>X</Button>}
                    </div>
                ))}
            </div>
        </>
    );

}
export default GetArticlesForHome;