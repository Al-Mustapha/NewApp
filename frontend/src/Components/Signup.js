import moment from 'moment';
import {Button, DatePicker, Input} from "antd";
import {useState} from "react";
import {signup} from "./Client";
import {useNavigate} from "react-router-dom";


const Signup = () => {

    const [reader, setReader] = useState(
        {
            firstName:"",
            middleName:"",
            lastName:"",
            dob:null,
            phoneNumber:"",
            country:"",
            state:"",
            username:"",
            password:""
        }
    );

    const handleDateChange = (date, dateString) =>{
        const selectedDate = moment(dateString, 'YYYY-MM-DD');
        setReader((prevData) => ({
                ...prevData,
                dob: date
            }));
    };

    const addReader = () => {
        signup(reader)
            .then(response => console.log(response))
            .catch(error => console.log(error))
    }

    const navi = useNavigate();

    const navToLogin = () => {
        navi("/login")
    }

    return(
     <>
    <h1>SIGN UP TO TOUR</h1>
    <form>
        <Input type="text" name ="firstName" placeholder="Enter your firstname" value={reader.firstName} onChange={(e) => setReader({...reader,[e.target.name]:e.target.value})}></Input><br></br>
        <Input type="text" name ="middleName" placeholder="Enter your middlename" value={reader.middleName} onChange={(e) => setReader({...reader,[e.target.name]:e.target.value})}></Input><br></br>
        <Input type="text" name ="lastName" placeholder="Enter your lastname" value={reader.lastName} onChange={(e) => setReader({...reader,[e.target.name]:e.target.value})}></Input><br></br>
        <DatePicker type="text" style ={{width:'300px',height:'50px'}} name ="dob" placeholder="Enter your date of birth" value={reader.dob} onChange={handleDateChange}></DatePicker><br></br>
        <Input type="text" name ="phoneNumber" placeholder="Enter your phone number" value={reader.phoneNumber} onChange={(e) => setReader({...reader,[e.target.name]:e.target.value})}></Input><br></br>
        <Input type="text" name ="country" placeholder="Enter your country" value={reader.country} onChange={(e) => setReader({...reader,[e.target.name]:e.target.value})}></Input><br></br>
        <Input type="text" name ="state" placeholder="Enter your state" value={reader.state} onChange={(e) => setReader({...reader,[e.target.name]:e.target.value})}></Input><br></br>
        <Input type="text" name ="username" placeholder="Enter your username" value={reader.username} onChange={(e) => setReader({...reader,[e.target.name]:e.target.value})}></Input><br></br>
        <Input type="text" name ="password" placeholder="Enter your password" value={reader.password} onChange={(e) => setReader({...reader,[e.target.name]:e.target.value})}></Input><br></br>
        <Button onClick={addReader}>SIGN UP</Button>
        <Button onClick={navToLogin}>LOGIN</Button>
    </form>

     </>
    )
}
export default Signup;