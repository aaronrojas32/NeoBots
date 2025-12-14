<div align="center">

#  NeoBots

**Simple bots for neoforge**

[![NeoForge](https://img.shields.io/badge/NeoForge-21.8.52-orange?style=for-the-badge&logo=curseforge)](https://neoforged.net/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.8-green?style=for-the-badge&logo=minecraft)](https://www.minecraft.net/)

---

</div>

##  Usage

### Spawning a Bot

Stand where you want the bot to appear and run:
```
/bot spawn <name>
```

The bot will spawn at your exact position and rotation.

### Removing a Bot

To despawn a specific bot:
```
/bot despawn <name>
```

### Listing All Bots

View all active bots in your world:
```
/bot list
```

## Commands

| Command | Description | Permission Level |
|---------|-------------|------------------|
| `/bot spawn <name>` | Spawns a new bot at your position | 2 (Operator) |
| `/bot despawn <name>` | Removes a bot from the world | 2 (Operator) |
| `/bot list` | Lists all active bots | 2 (Operator) |

> **Note**: In single-player, you automatically have operator permissions.

## Development

### Building from Source

```bash
# Clone the repository
git clone https://github.com/yourusername/NeoBots.git
cd NeoBots

# Build the mod
./gradlew build

# The compiled jar will be in build/libs/
```

### Project Structure

```
NeoBots/
├── src/main/java/dev/greendev/neobots/
│   ├── bot/           # Bot management logic
│   ├── client/        # Client-side rendering
│   ├── command/       # Command implementations
│   ├── entity/        # Custom entity definitions
│   └── event/         # Event handlers
└── src/main/resources/
    └── assets/neobots/
        └── lang/      # Translations
```

## Contributing

Contributions are welcome! Here's how you can help:

1.  Fork the repository
2.  Create a feature branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

##  Acknowledgments

- Built with [NeoForge](https://neoforged.net/)
- Inspired by the Minecraft modding community
- Special thanks to all contributors

---

<div align="center">
⭐ Star this repository if you found it helpful!

[Report Bug](https://github.com/aaronrojas32/NeoBots/issues) • [Request Feature](https://github.com/aaronrojas32/NeoBots/issues)

</div>
