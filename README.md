### Serving Frontend

In most cases you'll want to serve frontend as static resources 
via a web severer (e.g. nginx/Apache).
However, it's possible to serve the frontend straight from the Spring Boot app.
Use `-PdeployFrontend=true` property to include the frontend to the backend build pipeline.
You can customize context path of the frontend using `PUBLIC_URL` env variable.
E.g. to serve the frontend from the root:

```
PUBLIC_URL=/; gw bootRun -PdeployFrontend=true
```
