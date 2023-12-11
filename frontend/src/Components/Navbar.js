import ButtonGroup from "antd/es/button/button-group";
import {Button, Select} from "antd";
import {useNavigate} from "react-router-dom";
import jwt from 'jwt-decode';
import Cookies from 'js-cookie';
import {} from 'react-icons/fa'

const Navbar =() =>{
    const navigate = useNavigate();
    const handleRouteToAllNews = () => {
        navigate("/allnews");
    }

    const routeToHome =()=>{
     navigate("/homepage");
    }

    const routeToServices =() => {
        navigate("/services");
    }

    const routeToContact = () => {
        navigate("/contact");
    }

    const token = Cookies.get("myCookie");
    const extractedToken = jwt(token);

    return (
        <>
            <div style={{padding:'30px',backgroundColor:'darkslategrey',width:'100%',height:'100px'}}>
                <ButtonGroup style={{backgroundColor:'darkslategrey',padding:'10px'}}>
                    <Button style={{backgroundColor:'transparent',color:'white'}} onClick={routeToHome}>HOME</Button>
                    <Button style={{backgroundColor:'transparent',color:'white'}} onClick={handleRouteToAllNews}>NEWS</Button>
                    <Button style={{backgroundColor:'transparent',color:'white'}} onClick={routeToServices}>SERVICES</Button>
                    <Button style={{backgroundColor:'transparent',color:'white'}} onClick={routeToContact}>CONTACT</Button>
                </ButtonGroup>
                <select style={{float:'right',width:'200px',height:'30px'}}>
                    <option value="default" style={{color:'black'}}>WELCOME, {extractedToken.sub.toUpperCase()}</option>
                    <option value="logout">LOGOUT</option>
                </select>
            </div>
        </>
    )
}
export default Navbar;