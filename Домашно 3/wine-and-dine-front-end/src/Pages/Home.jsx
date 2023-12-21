import "./Home.css";
import Map from "../components/Map";
import Searchbar from "../components/Searchbar";
import SearchResults from "../components/SearchResults";
import {MapContext, MapDataProvider} from "../MapContext";
import {useContext, useEffect, useState} from "react";
import Review from "../components/Review";

const Home = () => {
  const {highlighted} = useContext(MapContext);
  const [reviewVisibility, setReviewVisibility] = useState(false);
  const [reviewData, setReviewData] = useState([]);
  const [reviewScore, setReviewScore] = useState(0);

  useEffect(() => {
      if(highlighted==null) setReviewVisibility(false);
  }, [highlighted]);

  const toggleReview = () => {
      if(highlighted!=null) {
          fetchReviews().then(setReviewVisibility(!reviewVisibility));
          fetchScore();
      }
  };

  async function fetchReviews() {
      const response = await fetch(
          "http://localhost:8080/api/review/all/" + highlighted.id
      );
      const data = await response.json();
      setReviewData(data);
  }

  async function fetchScore() {
    const response = await fetch(
        "http://localhost:8080/api/review/score/" + highlighted.id
    );
    const data = await response.json();
    setReviewScore(data);
  }


  return (
    <div className="home">
        <Searchbar />
        <div className={"horizontal-flex"}>
          <Map />
          <SearchResults />
        </div>
        <div className="buttons">
          <button>Добиј Рута</button>|
          <button disabled={highlighted == null}
                  onClick={toggleReview}
          >Читај Мислење</button>|
          <button>Додади Мислење</button>
        </div>

        {reviewVisibility && <div style={{padding: "2rem"}} className={'review-section'}>

            <h3>Просечна оценка: {reviewScore.toFixed(2)}</h3>


            <div className={'horizontal-flex review-container'}>
                {reviewVisibility && reviewData.map(rev => <Review review={rev} key={rev.id}/>) /*mora dvojna proverka*/}
            </div>

        </div>}


    </div>
  );
};

export default Home;
