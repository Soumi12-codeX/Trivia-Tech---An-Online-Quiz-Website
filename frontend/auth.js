// Simple token-based authentication helpers

const AUTH_TOKEN_KEY = 'mcq_portal_auth_token';
const USER_ROLE_KEY = 'mcq_portal_user_role';
const TEACHER_EMAIL_KEY = 'teacher_email';

// Save token & role & email
const setAuth = (token, role, email) => {
    localStorage.setItem(AUTH_TOKEN_KEY, token);
    localStorage.setItem(USER_ROLE_KEY, role);
    if (role === 'TEACHER') {
        localStorage.setItem(TEACHER_EMAIL_KEY, email);
    }
};

const getToken = () => localStorage.getItem(AUTH_TOKEN_KEY);
const getTeacherEmail = () => localStorage.getItem(TEACHER_EMAIL_KEY);

const isLoggedIn = () => getToken() !== null;

const logout = () => {
    localStorage.removeItem(AUTH_TOKEN_KEY);
    localStorage.removeItem(USER_ROLE_KEY);
    localStorage.removeItem(TEACHER_EMAIL_KEY);
    window.location.href = 'teacher-login.html';
};
