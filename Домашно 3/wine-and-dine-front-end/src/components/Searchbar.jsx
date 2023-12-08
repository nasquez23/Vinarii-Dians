import "./Searchbar.css"
import {useContext, useEffect, useState} from "react";
import {MapContext} from "../MapContext";

export default function Searchbar(){
    const {wineries, setWineries} = useContext(MapContext);
    const [searchTerm, setSearchTerm] = useState("");

    const clear = async () =>{
        setSearchTerm("");
        await search();
    }
    useEffect(() => {
        search()
    }, []);

    async function search(){
        const response = await fetch("http://localhost:8080/api/search?query="+searchTerm)
        const data = await response.json();
        setWineries(data);
    }


    return (<div id={'Search'}>
        <input type={'text'}
               name={'winery-search'}
               value={searchTerm}
               placeholder={'Пребарувајте винарии по име'}
               onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button onClick={() => search()}>
            Пребарувај
        </button>
        <button onClick={() => clear()}>
            Исчисти
        </button>
    </div>);
}