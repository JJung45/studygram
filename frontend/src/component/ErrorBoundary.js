import { useEffect } from "react";

export default function ErrorBoundary({ children }) {
  const captureReject = (e) => {
    e.preventDefault();

    // e.reason이 Error의 인스턴스일 경우
    if (e.reason instanceof Error) {
      window.alert(e.reason.response.data.message);
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