package poc

trait Config {

  def bootstrapServers = {
    Option(System.getProperty("bootstrap.servers")).getOrElse {
      throw new IllegalArgumentException("Environment variable bootstrap.servers is missing. Start the application with -Dbootstrap.servers=\"localhost:9092\"")
    }
  }
}
