import {Navigate} from "react-router-dom";
import Cookies from 'js-cookie';
import jwt from 'jwt-decode'

const PrivateRoute = ({children}) => {
    const myCookie = Cookies.get("myCookie");
    const decodedToken = jwt(myCookie);
    console.log(decodedToken)
    const myDate = new Date();
        return decodedToken.exp >= myDate.getDate()  ? children : <Navigate to="/"></Navigate>
}
export default PrivateRoute;