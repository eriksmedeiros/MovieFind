import {useParams} from 'react-router-dom';
import {useEffect, useState} from 'react';
import axios from 'axios';

import { FaStar } from "react-icons/fa";
import { BsFillFileEarmarkTextFill } from "react-icons/bs";
import { CiCalendarDate } from "react-icons/ci";

import Comments from '../../components/Comments/Comments';

import './Movie.css';

const API_URL = import.meta.env.VITE_API_URL;

const Movie = () => {
  const {id} = useParams();
  console.log("ID do filme:", id);
  const [movie, setMovie] = useState(null);

  const getMovies = async (url) => {
      const response = await axios.get(url);
      setMovie(response.data);
  }

  // const formatCurrency = (number) => {
  //     return number.toLocaleString("en-US", {
  //         style: "currency",
  //         currency: "USD"
  //     })
  // }

  useEffect(() => {
    if (id) { 
      const url = `${API_URL}/api/movies/${id}`;
      console.log("Buscando dados na URL:", url);
      getMovies(url);
    }
  }, [id, API_URL]);
  

  console.log(movie);

  return (
    <div className="movie-page">
      {!movie ? (
        <p>Carregando...</p>
      ) : (
        <>
          <div className="header">
            <img
              src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`}
              alt={movie.title}
            />
            <h1>{movie.title}</h1>
            <p>
              <FaStar />
              {movie.vote_average.toFixed(1)}
            </p>
          </div>
  
          <div className="info">
            <h3>
              <BsFillFileEarmarkTextFill /> Descrição:
            </h3>
            <p>{movie.overview}</p>
          </div>
  
          <div className="info">
            <h3>
              <CiCalendarDate /> Data de Lançamento:
            </h3>
            <p>{new Date(movie.release_date).toLocaleDateString("pt-BR")}</p>
          </div>

          <div className="comments">
            <Comments movieId={movie.id}/>
          </div>
        </>
      )}
    </div>
  );
}

export default Movie;