import { useState, useEffect, useContext } from "react";
import "./Comments.css";
import PropTypes from "prop-types";
import { AuthContext } from "../../context/AuthContext";

const Comments = ({ movieId }) => {
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState("");
  const { isAuthenticated, token } = useContext(AuthContext); // Pegando o token

  useEffect(() => {
    fetch(`http://localhost:8080/api/movies/${movieId}/comments`)
      .then((res) => res.json())
      .then((data) => setComments(data))
      .catch((err) => console.error("Erro ao buscar comentários:", err));
  }, [movieId]);

  const handleAddComment = async () => {
    if (!newComment.trim()) return;
    if (!isAuthenticated) {
      alert("Você precisa estar logado para comentar.");
      return;
    }

    const commentData = {
      text: newComment,
    };

    try {
      const response = await fetch(
        `http://localhost:8080/api/movies/${movieId}/comments`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`, // Adicionando token aqui
          },
          body: JSON.stringify(commentData),
        }
      );

      if (!response.ok) throw new Error("Erro ao adicionar comentário");

      const savedComment = await response.json();
      setComments([...comments, savedComment]);
      setNewComment("");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="comments-container">
      <h3>Comentários</h3>

      <ul>
        {comments.map((comment, index) => (
          <li key={index}>{comment.text}</li>
        ))}
      </ul>

      {isAuthenticated ? (
        <div className="comment-form">
          <input
            type="text"
            placeholder="Escreva um comentário..."
            value={newComment}
            onChange={(e) => setNewComment(e.target.value)}
          />
          <button onClick={handleAddComment}>Enviar</button>
        </div>
      ) : (
        <p className="login-warning">Entre para comentar.</p>
      )}
    </div>
  );
};

Comments.propTypes = {
  movieId: PropTypes.string.isRequired,
};

export default Comments;