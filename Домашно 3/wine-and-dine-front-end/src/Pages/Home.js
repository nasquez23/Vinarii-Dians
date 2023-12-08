import './Home.css';
import Map from "../components/Map";
import Searchbar from "../components/Searchbar";
import SearchResults from "../components/SearchResults";
import {MapDataProvider} from "../MapContext";

const Home = () => {
    return (
        <div className='home'>
            <MapDataProvider>
                <Searchbar/>
                <div className={'horizontal-flex'}>
                    <Map/>
                    <SearchResults/>
                </div>
            </MapDataProvider>
        </div>
    )
}

export default Home;