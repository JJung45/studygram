import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function ErrorBoundary({ children }) {

  const navigate = useNavigate();
  const captureReject = (e) => {
    e.preventDefault();

    // e.reason이 Error의 인스턴스일 경우
    if (e.reason instanceof Error) {
      window.alert(e.reason.message === "undefined" ? e.reason.response.data.message : e.reason.message);
      navigate(-1);
    }
  };

  useEffect(() => {
    window.addEventListener('unhandledrejection', captureReject);

    return () => {
      window.removeEventListener('unhandledrejection', captureReject);
    }
  }, []);

  return children;
}