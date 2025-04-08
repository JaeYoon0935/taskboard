# 1단계: 빌드용 (Node.js)
FROM node:18 AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# 2단계: 실행용 (Nginx)
FROM nginx:alpine

# 커스텀 nginx.conf 복사
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 빌드된 정적 파일 복사
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
