import zio._
import zio.http._

object FlinkZioRestApp extends ZIOAppDefault {

  val flinkUrl = "http://localhost:8081" // Adjust if needed
  // Replace YOUR_JAR_ID with the actual jar id from the Flink dashboard.
  val jarRunEndpoint = s"$flinkUrl/jars/0fa8b0f3-a3e4-4184-b213-412690ebfb2f_FlinkJob-assembly-0.1.jar/run"

  // JSON payload for running the Flink job.
  val jobPayload =
    """{
      |  "entryClass": "com.example.MyFlinkJob",
      |  "programArgs": "Hello Flink with ZIO"
      |}""".stripMargin

  def submitFlinkJob(client: Client): ZIO[Scope, Throwable, String] = for {
    url     <- ZIO.fromEither(URL.decode(jarRunEndpoint))
    request  = Request
                .post(url, Body.fromString(jobPayload))
                .setHeaders(Headers(Header.ContentType(MediaType.application.json)))
    response <- client.request(request)
    body    <- response.body.asString
  } yield body

  override def run: ZIO[Any, Throwable, Unit] =
    ZIO.scoped {
      Client.default.build.flatMap { env =>
        val client = env.get[Client]
        submitFlinkJob(client)
      }
    }
    .flatMap(body => Console.printLine(s"Flink Response: $body"))
    .tapError(err => ZIO.logError(s"Failed to submit Flink job: $err"))
}
