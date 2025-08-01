import axios from "axios"

let requestInterceptor
let responseInterceptor

export const setupAxiosInterceptors = () => {
  return new Promise((resolve) => {

    // 기존 요청 인터셉터 제거 (중복 방지)
    if (requestInterceptor !== undefined) {
      axios.interceptors.request.eject(requestInterceptor)
    }

    // 요청 인터셉터 등록 (토큰 자동 추가)
    requestInterceptor = axios.interceptors.request.use(
      (config) => {
        const token = localStorage.getItem("token")
       /*
        if (token) {
          config.headers.Authorization = `Bearer ${token}`
        } else {
          console.log("token 없음.")
        }
          */
        return config
      }, 
      (error) => Promise.reject(error)
    )

    // 기존 응답 인터셉터 제거 (중복 방지)
    if (responseInterceptor !== undefined) {
      axios.interceptors.response.eject(responseInterceptor)
    }

    // 응답 인터셉터 등록 (401 응답 시 로그아웃 처리)
    responseInterceptor = axios.interceptors.response.use(
      (response) => {
        console.log("[Axios Response]:", response)
        return response
      },
      (error) => {
        if (error.response && error.response.status === 401) {
          localStorage.removeItem("token")
          window.location.href = "/login"
        }
        return Promise.reject(error)
      }
    )

    resolve()
  })
}
