import path from "node:path";

const ROOT = "/home/user/-";

export const FONT_FILES: string[] = [
  path.join(ROOT, "node_modules/@expo-google-fonts/inter/400Regular/Inter_400Regular.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/inter/500Medium/Inter_500Medium.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/inter/600SemiBold/Inter_600SemiBold.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/inter/700Bold/Inter_700Bold.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/inter/800ExtraBold/Inter_800ExtraBold.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/jetbrains-mono/400Regular/JetBrainsMono_400Regular.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/jetbrains-mono/600SemiBold/JetBrainsMono_600SemiBold.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/jetbrains-mono/700Bold/JetBrainsMono_700Bold.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/ibm-plex-serif/500Medium/IBMPlexSerif_500Medium.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/ibm-plex-serif/600SemiBold/IBMPlexSerif_600SemiBold.ttf"),
  path.join(ROOT, "node_modules/@expo-google-fonts/ibm-plex-serif/700Bold/IBMPlexSerif_700Bold.ttf"),
];

export const FONTS = {
  sans: "Inter",
  mono: "JetBrains Mono",
  serif: "IBM Plex Serif",
};
