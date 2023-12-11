import {Button, Input} from "antd";
import {useState} from "react";
import {authUser} from "./Client";
import {useNavigate} from "react-router-dom";

const Login = () => {

    const navi = useNavigate();
    const navToSignup = () => {
        navi("/signup")
    }
    const [usDetails, setUsDetails] = useState({
        username: "",
        password: ""
    });

    const [status,setStatus] = useState(401);
    const handleChange = (e) => {
        const x = e.target.value;
        setUsDetails({...usDetails, [e.target.name]:x});
    }

    const authenticate = () => {
        authUser(usDetails.username, usDetails.password)
            .then((data) => {
                document.cookie = `myCookie=${data.data};Secure;SameSite=Strict;`;
                setStatus(200)
                }
            )
            .catch(error => console.log(error))
    };

    if(status == 200) {
        navi("/homepage");
    }

    return (
        <div className="Login">
            <h1>LOGIN TO TOUR</h1>
            <form>
            <Input type = "text" name = "username" placeholder="Enter your username" value ={usDetails.username} onChange = {handleChange}></Input><br></br>
            <Input type = "text" name="password" placeholder="Enter your password" value={usDetails.password} onChange = {handleChange}></Input><br></br>
            <Button onClick = {authenticate}>LOGIN</Button>
            <Button onClick={navToSignup}>SIGN UP</Button>
            </form>
        </div>
    )
}
export default Login;