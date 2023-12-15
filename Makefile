# Define variables
DOCKER_COMPOSE_FILE = docker-compose.yml

# Build the Docker image
build:
	docker-compose -f $(DOCKER_COMPOSE_FILE) build

# Run the Docker containers
up:
	docker-compose -f $(DOCKER_COMPOSE_FILE) up -d

# Stop the Docker containers
down:
	docker-compose -f $(DOCKER_COMPOSE_FILE) down

# Clean up Docker volumes (use with caution, it removes all volumes)
clean:
	docker-compose -f $(DOCKER_COMPOSE_FILE) down -v --remove-orphans

# Show logs of the running containers
logs:
	docker-compose -f $(DOCKER_COMPOSE_FILE) logs -f

# Build and run the Docker containers
run: build up

# Stop containers and clean up volumes
stop: down clean

# Help command to display available Makefile commands
help:
	@echo "Usage: make [command]"
	@echo ""
	@echo "Commands:"
	@echo "  build       Build Docker containers"
	@echo "  up          Run Docker containers in detached mode"
	@echo "  down        Stop Docker containers"
	@echo "  clean       Stop containers and remove volumes (use with caution)"
	@echo "  logs        Show logs of the running containers"
	@echo "  run         Build and run Docker containers"
	@echo "  stop        Stop containers and clean up volumes"
	@echo "  help        Show available Makefile commands"

# By default, show help command
.DEFAULT_GOAL := help
