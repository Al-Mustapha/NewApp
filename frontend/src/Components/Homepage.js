import GetArticlesForHome from "./GetArticlesForHome";
import Navbar from "./Navbar";
import Footer from "./Footer";

const Homepage = () => {
    return (
      <>
          <Navbar></Navbar>
          <br></br>
          <GetArticlesForHome></GetArticlesForHome>
          <Footer></Footer>
      </>
    );
}
export default Homepage;