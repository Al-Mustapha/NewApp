import {Button, Input} from "antd";
import './App.css';
import {useEffect, useState} from "react";
import {BrowserRouter, Route, Routes, useNavigate} from "react-router-dom";

import Login from "./Components/login";
import Homepage from "./Components/Homepage";
import Signup from "./Components/Signup";
import AddArticle from "./Components/AddArticle";
import ViewArticleDetails from "./Components/ViewArticleDetails";
import EditArticle from "./Components/EditArticle";
import AllNews from "./Components/AllNews";
import PrivateRoute from "./Components/PrivateRoute";
import Contact from "./Components/Contact";

function Root(){

    return (
        <div>
            <Routes>
                <Route path="/login" element={<Login/>}></Route>
                <Route path="/homepage" element={
                <PrivateRoute>
                    <Homepage/>
                </PrivateRoute>
                }></Route>
                <Route path="/signup" element={<Signup/>}></Route>
                <Route path="/addarticle" element={
                    <PrivateRoute>
                        <AddArticle/>
                    </PrivateRoute>
                }></Route>
                <Route path="/ViewArticleDetails/:id" element={
                    <PrivateRoute>
                        <ViewArticleDetails/>
                    </PrivateRoute>
                }></Route>
                <Route path="/editAnArticle/:id" element={
                    <PrivateRoute>
                        <EditArticle/>
                    </PrivateRoute>
                }></Route>
                <Route path="/allnews" element=

                    {
                    <PrivateRoute>
                        <AllNews/>
                    </PrivateRoute>
                }>
                </Route>
                <Route path="/contact" element={<Contact/>}></Route>
            </Routes>
        </div>
    )
};

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Root/>
            </BrowserRouter>
        </div>
    );
}
export default App;