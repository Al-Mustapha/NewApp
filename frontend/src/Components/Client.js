import axios, {head} from "axios";
import Cookies from "js-cookie";

const token = Cookies.get("myCookie")


const checkStatus = (response) => {
    if(response.ok){
        return response;
    }
    else {
        const error = new Error(response.statusText)
        error.response = response;
        return Promise.reject(error);
    }
}

    export const authUser = (username, password) =>
        axios.post("http://localhost:8080/authenticate",  {
            username:username, password:password
        });

    export const signup = (ReaderModel) =>
        axios.post("http://localhost:8080/reader/createAccount", ReaderModel);

    export const createArticle = (title, file, content) =>
        axios.post("http://localhost:8080/article/createArticle", {title,file, content},
            {
            headers: {
                'Content-Type':'multipart/form-data',
                'Authorization': 'Bearer ' + token
            },
        }
        );

    export const getNineArticles = () =>
        fetch("http://localhost:8080/article/getSixArticles")
        .then(checkStatus);

    export const getOneArticle = (id) =>
       axios.get("http://localhost:8080/article/getOneArticle/" + id,
           {
               headers: {
                   'Authorization':'Bearer ' + token,
               },
           }
           );

    export const editAnArticle = (id, title, file, content) =>
        axios.put("http://localhost:8080/article/editArticle/" + id, {title, file, content}, {
            headers:{
                'Content-Type':'multipart/form-data',
                'Authorization':'Bearer ' + token,
            }
        });

    export const getEveryArticles =() =>
        axios.get("http://localhost:8080/article/getAllArticles",
            {
                headers: {
                    'Authorization':'Bearer ' + token,
                },
            });

    export const deleteSingleArticle = (id) =>
        axios.delete("http://localhost:8080/article/deleteAnArticle/" + id,
            {
                headers: {
                    'Authorization': 'Bearer ' + token,
                }
            }
        )

    export const sendMessage = (myMessage) =>
        axios.post("http://localhost:8080/message/sendMessage", myMessage);