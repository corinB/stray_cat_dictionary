import React, { useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../contexts/AuthContext';
import './Community.css';
import API_BASE_URL from '../../utils/config';

function Community() {
  const [posts, setPosts] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [sortBy, setSortBy] = useState('new'); // 'new' or 'popular'
  const navigate = useNavigate();
  const [isLoggedIn] = useContext(AuthContext);

  useEffect(() => {
    fetchPosts();
  }, [sortBy, searchTerm]);

  const fetchPosts = async () => {
    try {
      const response = await fetch(`${API_BASE_URL}/api/posts?sort=${sortBy}&search=${searchTerm}`, {
        credentials: 'include'
      });
      if (response.ok) {
        const data = await response.json();
        setPosts(data.posts);
      }
    } catch (error) {
      console.error('게시글 로딩 실패:', error);
    }
  };

  const handleSearch = (e) => {
    e.preventDefault();
    // 폼 제출 시 이미 useEffect가 실행되므로 추가 호출 필요 없음
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString('ko-KR', {
      year: 'numeric',
      month: '2-digit',
      day: '2-digit'
    });
  };

  return (
    <div className="community-container">
      <div className="community-content">
        <div className="community-title-section">
          <div className="title-icon-wrapper">
            <h1 className="page-title">게시글 목록</h1>
          </div>
          {isLoggedIn &&(
            <button 
              className="write-button"
              onClick={()=> navigate('/community/write')}
            >
              <span>글쓰기</span> 
            </button>
          )}
        </div>

        <div className="search-section">
          <form onSubmit={handleSearch} className="search-form">
            <input
              type="text"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              placeholder="검색어 입력"
              className="search-input"
            />
            <button type="submit" className="search-button">
              <span>🔍</span> 검색
            </button>
          </form>
        </div>

        <div className="sort-section">
          <div className="sort-label">
            <span>정렬 기준</span>
          </div>
          <div className="sort-buttons">
            <button
              className={`sort-button ${sortBy === 'new' ? 'active' : ''}`}
              onClick={() => setSortBy('new')}
            >
              <span className="sort-btn-icon">⏱️</span> 최신순
            </button>
            <button
              className={`sort-button ${sortBy === 'popular' ? 'active' : ''}`}
              onClick={() => setSortBy('popular')}
            >
              <span className="sort-btn-icon">🔥</span> 인기순
            </button>
          </div>
        </div>

        <div className="table-container">
          <table className="posts-table">
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
                <th>좋아요</th>
                <th>신고수</th>
              </tr>
            </thead>
            <tbody>
              {posts.length > 0 ? (
                posts.map((post, index) => (
                  <tr key={post.id} onClick={() => navigate(`/community/post/${post.id}`)}>
                    <td>{posts.length - index}</td>
                    <td className="post-title-cell">{post.title}</td>
                    <td>{post.nickname}</td>
                    <td>{formatDate(post.wdate)}</td>
                    <td>{post.view}</td>
                    <td>{post.like_count}</td>
                    <td>{post.report}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="7" className="no-posts">
                    게시글이 없습니다.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default Community; 